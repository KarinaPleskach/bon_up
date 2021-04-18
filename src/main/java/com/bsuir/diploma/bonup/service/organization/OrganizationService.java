package com.bsuir.diploma.bonup.service.organization;

import com.bsuir.diploma.bonup.dto.model.NameDto;
import com.bsuir.diploma.bonup.dto.model.organization.OrganizationUserDto;
import com.bsuir.diploma.bonup.dto.model.organization.OrganizationWithPhotoDto;
import com.bsuir.diploma.bonup.dto.model.organization.SetCityOrganization;
import com.bsuir.diploma.bonup.dto.model.organization.SetDescriptionOrganization;
import com.bsuir.diploma.bonup.dto.model.organization.SetXYOrganization;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import java.util.List;

public interface OrganizationService {
    void checkAdminOrganizationPermission(UserLogin user, String lang);

    Organization findByNameAndUser(String name, UserLogin userLogin, String lang);

    List<Organization> findByUser(UserLogin userLogin);

    int getNumberOfOrganizations(TokenDto tokenDto, String lang);

    List<OrganizationWithPhotoDto> getOrganizations(TokenDto tokenUser, String lang);

    OrganizationUserDto getOrganization(NameDto nameDto, String lang);

    void setXY(SetXYOrganization setXYOrganization, String lang);

    void setCity(SetCityOrganization setCityOrganization, String lang);

    void setDescription(SetDescriptionOrganization setDescriptionOrganization, String lang);
}
