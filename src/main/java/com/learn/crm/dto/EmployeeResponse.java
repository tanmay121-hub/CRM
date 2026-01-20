package com.learn.crm.dto;

import com.learn.crm.enums.EmployeeStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeResponse {
    private String name;
    private String email;
    private String phone;
    private String designation;
    private LocalDate joiningDate;
    private EmployeeStatus status;
    private BigDecimal salary;
    private String username;
}
