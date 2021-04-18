package com.bsuir.diploma.bonup.dto.response.task.additional;

import com.bsuir.diploma.bonup.dto.model.task.additional.PublicTypeDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseWithTypes {
    private Boolean isSuccess;
    private String message;
    private List<PublicTypeDto> types;
}
