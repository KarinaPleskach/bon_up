package com.bsuir.diploma.bonup.dto.model.user.auth.organization;

import lombok.Data;

@Data
public class AdminAuthUser {
    private String adminToken;
    private String login;
    private String password;
    private String name;
}
