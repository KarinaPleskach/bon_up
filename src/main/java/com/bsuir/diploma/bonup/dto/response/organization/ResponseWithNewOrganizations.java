package com.bsuir.diploma.bonup.dto.response.organization;

import com.bsuir.diploma.bonup.dto.model.organization.NewOrganizationWithPhoto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseWithNewOrganizations {
    private List<NewOrganizationWithPhoto> list;
}
