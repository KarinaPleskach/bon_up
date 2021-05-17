package com.bsuir.diploma.bonup.service.organization;

import com.bsuir.diploma.bonup.dto.model.organization.NewOrganizationWithPhoto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.OrganizationNewDto;
import java.util.List;

public interface OrganizationNewService {
    void create(OrganizationNewDto organizationNewDto, String lang);

    List<NewOrganizationWithPhoto> getOrganizations(TokenDto tokenUser, String lang);
}
