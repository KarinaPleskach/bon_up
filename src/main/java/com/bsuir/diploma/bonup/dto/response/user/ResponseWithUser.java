package com.bsuir.diploma.bonup.dto.response.user;

import com.bsuir.diploma.bonup.dto.converter.user.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseWithUser {
    private Boolean isSuccess;
    private String message;
    private UserInfoDto userInfo;
}
