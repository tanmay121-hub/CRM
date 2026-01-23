package com.learn.crm.controller;


import com.learn.crm.dto.EmployeeRequest;
import com.learn.crm.dto.EmployeeResponse;
import com.learn.crm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.createEmployee(request));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping("/assign/{pid}/{eid}")
    public ResponseEntity<String> issueProject(@PathVariable Long pid , @PathVariable Long eid,@RequestParam Boolean isManager){
        return ResponseEntity.ok(employeeService.assignProject(pid,eid,isManager));
    }
}