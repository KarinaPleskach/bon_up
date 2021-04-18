package com.bsuir.diploma.bonup.dto.converter.organization;

import com.bsuir.diploma.bonup.dto.model.organization.OrganizationUserDto;
import com.bsuir.diploma.bonup.model.AbstractEntity;
import com.bsuir.diploma.bonup.model.organization.Organization;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrganizationToOrganizationUserDtoConverter implements Converter<Organization, OrganizationUserDto> {

    @Override
    public OrganizationUserDto convert(Organization organization) {
        OrganizationUserDto organizationUserDto = new OrganizationUserDto();
        organizationUserDto.setName(organization.getName());
        organizationUserDto.setPhotos(organization.getPhotos().stream().map(AbstractEntity::getId).collect(Collectors.toList()));
        organizationUserDto.setX(organization.getXCoor());
        organizationUserDto.setY(organization.getYCoor());
        return organizationUserDto;
    }

}