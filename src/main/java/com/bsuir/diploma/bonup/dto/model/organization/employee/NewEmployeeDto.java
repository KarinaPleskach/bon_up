package com.bsuir.diploma.bonup.dto.model.organization.employee;

import lombok.Data;

@Data
public class NewEmployeeDto {
    private String token;
    private String login;
    private String password;
    private String name;
    private String organizationName;
}
