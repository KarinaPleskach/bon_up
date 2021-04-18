package com.bsuir.diploma.bonup.dto.model.user.auth.organization;

import lombok.Data;

@Data
public class NewOrganization {
    private String name;
    private Float x;
    private Float y;
    private Long cityId;
    private String adminToken;
    private String userOrganizationToken;
}
