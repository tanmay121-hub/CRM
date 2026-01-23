package com.learn.crm.service;

import com.learn.crm.dto.ProjectRequestDto;
import com.learn.crm.dto.ProjectResponseDto;
import com.learn.crm.enums.ProjectStatus;
import com.learn.crm.model.Project;
import com.learn.crm.repository.EmployeeRepo;
import com.learn.crm.repository.ProjectRepo;
import com.learn.crm.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ProjectRepo projectRepo;

    public String assignProject (ProjectRequestDto dto){

        if (projectRepo.existsByName(dto.getName())){
            throw new RuntimeException("Project Already exists");
        }
        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setStartDate(LocalDate.parse(dto.getStartDate()));
        project.setProjectStatus(dto.getProjectStatus());

        projectRepo.save(project);
        return "Assigned the project";
    }

    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

}
