package com.learn.crm.service;

import com.learn.crm.dto.DepartmentRequest;
import com.learn.crm.model.Department;
import com.learn.crm.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService{

    @Autowired
    private DepartmentRepo departmentRepo;

    public String addDepartment(DepartmentRequest department1){
        Department department = new Department();
        department.setName(department1.getName());
        department.setDescription(department1.getDescription());
        departmentRepo.save(department);
        return "Department Added Successfully";
    }

    public List<Department> AllDepartment(){
        return departmentRepo.findAll();
    }

}
