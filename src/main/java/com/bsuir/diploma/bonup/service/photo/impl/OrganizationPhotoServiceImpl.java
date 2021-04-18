package com.bsuir.diploma.bonup.service.photo.impl;

import com.bsuir.diploma.bonup.dto.model.organization.OrganizationPhotoDto;
import com.bsuir.diploma.bonup.dto.model.organization.SetDescriptionOrganization;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.exception.photo.NoSuchPhotoException;
import com.bsuir.diploma.bonup.exception.photo.PhotoAlreadyExistException;
import com.bsuir.diploma.bonup.exception.user.auth.AccessErrorException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserRole;
import com.bsuir.diploma.bonup.service.organization.OrganizationService;
import com.bsuir.diploma.bonup.service.photo.OrganizationPhotoService;
import com.bsuir.diploma.bonup.service.photo.PhotoService;
import com.bsuir.diploma.bonup.service.user.UserService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganizationPhotoServiceImpl implements OrganizationPhotoService {

    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private PhotoService photoService;


    @Override
    public void saveOrganizationPhoto(OrganizationPhotoDto organizationPhotoDto, String lang) {
        Organization organization = validateOrganizationPhotoDto(organizationPhotoDto, lang);
        if (organization.getPhotos().stream().anyMatch(photo -> photo.getId().equals(organizationPhotoDto.getPhotoId()))) {
            throw new PhotoAlreadyExistException(lang);
        }

        Photo photo = photoService.getPhoto(organizationPhotoDto.getPhotoId(), lang);
        organization.getPhotos().add(photo);
    }

    @Override
    public void deleteOrganizationPhoto(OrganizationPhotoDto organizationPhotoDto, String lang) {
        Organization organization = validateOrganizationPhotoDto(organizationPhotoDto, lang);
        if (organization.getPhotos().stream().noneMatch(photo -> photo.getId().equals(organizationPhotoDto.getPhotoId()))) {
            throw new NoSuchPhotoException(lang);
        }
        Photo photo = photoService.getPhoto(organizationPhotoDto.getPhotoId(), lang);
        organization.getPhotos().remove(photo);
        photoService.delete(photo, lang);
    }

    private Organization validateOrganizationPhotoDto(OrganizationPhotoDto organizationPhotoDto, String lang) {
        if (Objects.isNull(organizationPhotoDto) || Objects.isNull(organizationPhotoDto.getToken()) || Objects.isNull(organizationPhotoDto.getName()) || Objects.isNull(organizationPhotoDto.getPhotoId()))
            throw new NullValidationException(lang);
        UserLogin user = userService.findByToken(organizationPhotoDto.getToken(), lang);
        if (!userService.getUserRole(user).equals(UserRole.ROLE_ORGANIZATION_ADMIN))
            throw new AccessErrorException(lang);
        if (Objects.nonNull(organizationPhotoDto.getName())) {
            return organizationService.findByNameAndUser(organizationPhotoDto.getName(), user, lang);
        }
        throw new NullValidationException(lang);
    }

}
