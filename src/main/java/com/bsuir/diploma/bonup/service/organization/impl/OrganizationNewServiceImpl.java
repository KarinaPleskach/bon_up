package com.bsuir.diploma.bonup.service.organization.impl;

import com.bsuir.diploma.bonup.dao.organization.OrganizationNewDao;
import com.bsuir.diploma.bonup.dto.model.organization.NewOrganizationWithPhoto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.OrganizationNewDto;
import com.bsuir.diploma.bonup.exception.organization.NoSuchOrganizationException;
import com.bsuir.diploma.bonup.exception.organization.OrganizationWithSuchNameAlreadyExistException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.organization.OrganizationNew;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.task.additional.Category;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.service.organization.OrganizationNewService;
import com.bsuir.diploma.bonup.service.photo.PhotoService;
import com.bsuir.diploma.bonup.service.task.additional.CategoryService;
import com.bsuir.diploma.bonup.service.user.UserService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

    @Override
    public List<NewOrganizationWithPhoto> getOrganizations(TokenDto tokenUser, String lang) {
        validateTokenUser(tokenUser, lang);
        UserLogin user = userService.findByToken(tokenUser.getToken(), lang);
        return organizationNewDao.findByUserLogin(user).stream()
                .map(o -> {
                    NewOrganizationWithPhoto n = new NewOrganizationWithPhoto();
                    n.setAvailableStocksCount(o.getAvailableStocksCount());
                    n.setAvailableCouponsCount(o.getAvailableCouponsCount());
                    n.setAvailableTasksCount(o.getAvailableTasksCount());
                    n.setCategoryId(o.getCategory().getId());
                    n.setContactsPhone(o.getContactsPhone());
                    n.setContactsVK(o.getContactsVK());
                    n.setContactsWebSite(o.getContactsWebSite());
                    n.setDescriptionText(o.getDescriptionText());
                    n.setId(o.getId());
                    n.setTitle(o.getTitle());
                    n.setLocationCity(o.getLocationCity());
                    n.setLatitude(o.getLatitude());
                    n.setPhotoId(o.getPhoto().getId());
                    n.setLongitude(o.getLongitude());
                    n.setDirectorFirstName(o.getDirectorFirstName());
                    n.setDirectorLastName(o.getDirectorLastName());
                    n.setDirectorSecondName(o.getDirectorSecondName());
                    n.setLocationCountry(o.getLocationCountry());
                    n.setLocationStreet(o.getLocationStreet());
                    n.setLocationHomeNumber(o.getLocationHomeNumber());
                    return n;
                })
                .collect(Collectors.toList());
    }

    private void validateTokenUser(TokenDto tokenDto, String lang) {
        if (Objects.isNull(tokenDto) || Objects.isNull(tokenDto.getToken())) {
            throw new NullValidationException(lang);
        }
    }

    @Override
    public OrganizationNew findByNameAndUser(String name, UserLogin userLogin, String lang) {
        return organizationNewDao.findByTitleAndUserLogin(name, userLogin)
                .orElseThrow(() -> new NoSuchOrganizationException(lang));
    }

    @Override
    public OrganizationNew findByIdAndUser(Long id, UserLogin userLogin, String lang) {
        return organizationNewDao.findByIdAndUserLogin(id, userLogin)
                .orElseThrow(() -> new NoSuchOrganizationException(lang));
    }
}
