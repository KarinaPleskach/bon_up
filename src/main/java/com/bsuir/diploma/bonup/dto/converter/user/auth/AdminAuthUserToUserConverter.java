package com.bsuir.diploma.bonup.dto.converter.user.auth;

import com.bsuir.diploma.bonup.dto.model.user.auth.organization.AdminAuthUser;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdminAuthUserToUserConverter implements Converter<AdminAuthUser, UserLogin> {

    @Override
    public UserLogin convert(AdminAuthUser authUserDto) {
        return UserLogin.builder()
                .login(authUserDto.getLogin())
                .password(authUserDto.getPassword())
                .token(null)
                .build();
    }

}
