package com.bsuir.diploma.bonup.dto.converter.organization;

import com.bsuir.diploma.bonup.dto.model.organization.OrganizationWithPhotoDto;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.photo.Photo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrganizationToOrganizationWithPhotoDtoConverter implements Converter<Organization, OrganizationWithPhotoDto> {

    @Override
    public OrganizationWithPhotoDto convert(Organization organization) {
        OrganizationWithPhotoDto organizationWithPhoto = new OrganizationWithPhotoDto();
        organizationWithPhoto.setName(organization.getName());
        Photo photo = organization.getPhotos().stream().findFirst().orElse(null);
        organizationWithPhoto.setPhotoId(photo == null ? null : photo.getId());
        return organizationWithPhoto;
    }

}
