package com.learn.crm.service;

import com.learn.crm.dto.EmployeeRequest;
import com.learn.crm.dto.EmployeeResponse;
import com.learn.crm.enums.Role;
import com.learn.crm.model.Department;
import com.learn.crm.model.Employee;
import com.learn.crm.model.User;
import com.learn.crm.repository.DepartmentRepo;
import com.learn.crm.repository.EmployeeRepo;
import com.learn.crm.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private EmployeeResponse mapToResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        BeanUtils.copyProperties(employee, response);
        response.setUsername(employee.getUser().getUsername());
        return response;
    }

    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequest request) {

        if (employeeRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Employee creation failed: Email " + request.getEmail() + " already exists.");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(Role.USER);

        User savedUser = userRepo.save(newUser);


        Department department = departmentRepo.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Employee employee = new Employee();
        BeanUtils.copyProperties(request, employee);
        employee.setUser(savedUser);
        employee.setDepartment(department);

        if (request.getManagerId() != null) {
            Employee manager = employeeRepo.findById(request.getManagerId()).orElse(null);
            employee.setManager(manager);
        }

        Employee savedEmployee = employeeRepo.save(employee);
        return mapToResponse(savedEmployee);
    }

    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepo.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
