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
import org.springframework.stereotype.Service;

@Service
public class EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private UserRepo userRepo;

    private EmployeeResponse mapToResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        BeanUtils.copyProperties(employee, response);
        response.setUsername(employee.getUser().getUsername());
        return response;
    }

    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequest request) {


        // Create and Save the User (Login Info)
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword()); // In real app, encrypt this!
        newUser.setRole(Role.MANAGER.name()); // Default role

        User savedUser = userRepo.save(newUser);

        // Fetch the Department
        Department department = departmentRepo.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Create the Employee
        Employee employee = new Employee();

        BeanUtils.copyProperties(request, employee);

        employee.setUser(savedUser);
        employee.setDepartment(department);

        if (request.getManagerId() != null) {
            Employee manager = employeeRepo.findById(request.getManagerId())
                    .orElse(null);
            employee.setManager(manager);
        }

        //Save Employee
        Employee savedEmployee = employeeRepo.save(employee);

        // Convert to Response DTO
        return mapToResponse(savedEmployee);
    }
}
