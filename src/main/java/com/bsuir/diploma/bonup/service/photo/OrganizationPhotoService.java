package com.bsuir.diploma.bonup.service.photo;

import com.bsuir.diploma.bonup.dto.model.organization.OrganizationPhotoDto;

public interface OrganizationPhotoService {
    void saveOrganizationPhoto(OrganizationPhotoDto organizationPhotoDto, String lang);
    void deleteOrganizationPhoto(OrganizationPhotoDto organizationPhotoDto, String lang);
}
