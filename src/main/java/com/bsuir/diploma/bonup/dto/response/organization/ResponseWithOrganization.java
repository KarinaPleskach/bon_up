package com.bsuir.diploma.bonup.dto.response.organization;

import com.bsuir.diploma.bonup.dto.model.organization.OrganizationUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseWithOrganization {
    private Boolean isSuccess;
    private String message;
    private OrganizationUserDto organization;
}
