package com.bsuir.diploma.bonup.dto.model.user.auth;

import lombok.Data;

@Data
public class RegUserDto {
    private String login;
    private String password;
    private String name;
}
