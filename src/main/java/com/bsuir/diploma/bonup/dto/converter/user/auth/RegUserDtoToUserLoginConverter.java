package com.bsuir.diploma.bonup.dto.converter.user.auth;

import com.bsuir.diploma.bonup.dto.model.user.auth.RegUserDto;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegUserDtoToUserLoginConverter implements Converter<RegUserDto, UserLogin> {

    @Override
    public UserLogin convert(RegUserDto regUserDto) {
        return UserLogin.builder()
                .login(regUserDto.getLogin())
                .password(regUserDto.getPassword())
                .token(null)
                .build();
    }

}
