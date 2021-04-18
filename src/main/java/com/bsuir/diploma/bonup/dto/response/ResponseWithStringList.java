package com.bsuir.diploma.bonup.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseWithStringList {
    private Boolean isSuccess;
    private String message;
    private List<String> list;
}
