package com.bsuir.diploma.bonup.dto.model.user.auth;

import lombok.Data;

@Data
public class NewPasswordDto {
    private String token;
    private String password;
}
