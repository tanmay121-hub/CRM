package com.learn.crm.dto;

import com.learn.crm.enums.ProjectStatus;
import com.learn.crm.model.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ProjectRequestDto {

    private String name;
    private String description;
    private ProjectStatus projectStatus;

    private String startDate;
}
