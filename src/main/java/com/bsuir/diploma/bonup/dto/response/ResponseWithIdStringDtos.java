package com.bsuir.diploma.bonup.dto.response;

import com.bsuir.diploma.bonup.dto.model.IdStringDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseWithIdStringDtos {
    private Boolean isSuccess;
    private String message;
    private List<IdStringDto> list;
}
