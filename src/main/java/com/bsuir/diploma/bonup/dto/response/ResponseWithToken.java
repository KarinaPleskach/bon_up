package com.bsuir.diploma.bonup.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseWithToken {
    private Boolean isSuccess;
    private String message;
    private String token;
}
