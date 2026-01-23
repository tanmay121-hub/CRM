package com.learn.crm.controller;

import com.learn.crm.dto.ProjectRequestDto;
import com.learn.crm.dto.ProjectResponseDto;
import com.learn.crm.model.Project;
import com.learn.crm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ProjectRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectService.assignProject(dto));
    }
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects(){
        return ResponseEntity.ok(projectService.getAllProjects());
    }
}