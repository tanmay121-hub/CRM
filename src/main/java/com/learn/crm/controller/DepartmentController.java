package com.learn.crm.controller;

import com.learn.crm.dto.DepartmentRequest;
import com.learn.crm.model.Department;
import com.learn.crm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/createDepartment")
    public ResponseEntity<String> createDep(DepartmentRequest department){
        String response = departmentService.addDepartment(department);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Department>>findAll(){
        try{
            return new ResponseEntity<>(departmentService.AllDepartment(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
