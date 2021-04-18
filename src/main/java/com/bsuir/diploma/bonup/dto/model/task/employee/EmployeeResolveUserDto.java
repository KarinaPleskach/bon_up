package com.bsuir.diploma.bonup.dto.model.task.employee;

import lombok.Data;

@Data
public class EmployeeResolveUserDto {
    private Long id;
    private String userToken;
    private String employeeToken;
}
