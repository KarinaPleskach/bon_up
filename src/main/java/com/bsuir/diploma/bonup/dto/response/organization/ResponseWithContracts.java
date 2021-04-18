package com.bsuir.diploma.bonup.dto.response.organization;

import com.bsuir.diploma.bonup.dto.model.organization.AllContractDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseWithContracts {
    private Boolean isSuccess;
    private String message;
    private List<AllContractDto> list;
}
