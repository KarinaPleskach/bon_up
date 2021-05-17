package com.bsuir.diploma.bonup.service.organization.impl;

import com.bsuir.diploma.bonup.dao.organization.OrganizationNewDao;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.OrganizationNewDto;
import com.bsuir.diploma.bonup.exception.organization.OrganizationWithSuchNameAlreadyExistException;
import com.bsuir.diploma.bonup.model.organization.OrganizationNew;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.service.organization.OrganizationNewService;
import com.bsuir.diploma.bonup.service.photo.PhotoService;
import com.bsuir.diploma.bonup.service.task.additional.CategoryService;
import com.bsuir.diploma.bonup.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganizationNewServiceImpl implements OrganizationNewService {

    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrganizationNewDao organizationNewDao;

    @Override
    public void create(OrganizationNewDto organizationNewDto, String lang) {
        UserLogin user = userService.findByToken(organizationNewDto.getToken(), lang);
        if (organizationNewDao.findByTitle(organizationNewDto.getTitle()).isPresent()) {
            throw new OrganizationWithSuchNameAlreadyExistException(lang);
        }
        Photo photo = photoService.getPhoto(organizationNewDto.getPhotoId(), lang);
        Category category = categoryService.getById(organizationNewDto.getCategoryId(), lang);
        OrganizationNew organizationNew = OrganizationNew.builder()
                .availableCouponsCount(organizationNewDto.getAvailableCouponsCount())
                .availableStocksCount(organizationNewDto.getAvailableStocksCount())
                .availableTasksCount(organizationNewDto.getAvailableTasksCount())
                .contactsPhone(organizationNewDto.getContactsPhone())
                .contactsVK(organizationNewDto.getContactsVK())
                .contactsWebSite(organizationNewDto.getContactsWebSite())
                .descriptionText(organizationNewDto.getDescriptionText())
                .directorFirstName(organizationNewDto.getDirectorFirstName())
                .directorLastName(organizationNewDto.getDirectorLastName())
                .directorSecondName(organizationNewDto.getDirectorSecondName())
                .locationCity(organizationNewDto.getLocationCity())
                .locationCountry(organizationNewDto.getLocationCountry())
                .userLogin(user)
                .locationHomeNumber(organizationNewDto.getLocationHomeNumber())
                .locationStreet(organizationNewDto.getLocationStreet())
                .photo(photo)
                .title(organizationNewDto.getTitle())
                .category(category)
                .build();
        organizationNewDao.save(organizationNew);
    }

}
