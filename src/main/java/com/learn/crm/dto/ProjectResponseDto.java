package com.learn.crm.dto;

import com.learn.crm.enums.ProjectStatus;
import com.learn.crm.model.Employee;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class ProjectResponseDto {

    private Long id;
    private String name;
    private String description;
    private ProjectStatus projectStatus;

    private LocalDate startDate;
    private LocalDate endDate;
    private Set<Employee> teamMembers = new HashSet<>();
}
