package com.bsuir.diploma.bonup.dto.response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseWithMap {
    private Boolean isSuccess;
    private String message;
    private Map<Long, String> map;
}
