package com.bsuir.diploma.bonup.service.organization;

import com.bsuir.diploma.bonup.dto.model.user.auth.organization.OrganizationNewDto;

public interface OrganizationNewService {
    void create(OrganizationNewDto organizationNewDto, String lang);
}
