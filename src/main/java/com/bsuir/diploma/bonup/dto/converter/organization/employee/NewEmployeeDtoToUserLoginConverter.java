package com.bsuir.diploma.bonup.dto.converter.organization.employee;

import com.bsuir.diploma.bonup.dto.model.organization.employee.NewEmployeeDto;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NewEmployeeDtoToUserLoginConverter implements Converter<NewEmployeeDto, UserLogin> {

    @Override
    public UserLogin convert(NewEmployeeDto newEmployeeDto)  {
        return UserLogin.builder()
                .login(newEmployeeDto.getLogin())
                .password(newEmployeeDto.getPassword())
                .token(null)
                .build();
    }

}
