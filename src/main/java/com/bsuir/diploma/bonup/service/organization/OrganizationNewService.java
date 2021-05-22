package com.bsuir.diploma.bonup.service.organization;

import com.bsuir.diploma.bonup.dto.model.organization.NewOrganizationWithPhoto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.OrganizationNewDto;
import com.bsuir.diploma.bonup.model.organization.OrganizationNew;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import java.util.List;

public interface OrganizationNewService {
    void create(OrganizationNewDto organizationNewDto, String lang);

    List<NewOrganizationWithPhoto> getOrganizations(TokenDto tokenUser, String lang);

    OrganizationNew findByNameAndUser(String name, UserLogin userLogin, String lang);

    OrganizationNew findByName(String name, String lang);

    OrganizationNew findByIdAndUser(Long id, UserLogin userLogin, String lang);

    void modify(OrganizationNewDto organizationNewDto, String lang);
}
