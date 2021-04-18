package com.bsuir.diploma.bonup.service.organization.impl;

import com.bsuir.diploma.bonup.dao.organization.OrganizationRepository;
import com.bsuir.diploma.bonup.dao.translation.LanguageKeyRepository;
import com.bsuir.diploma.bonup.dao.translation.LanguageRepository;
import com.bsuir.diploma.bonup.dao.translation.LanguageTranslationRepository;
import com.bsuir.diploma.bonup.dto.converter.organization.OrganizationToOrganizationUserDtoConverter;
import com.bsuir.diploma.bonup.dto.converter.organization.OrganizationToOrganizationWithPhotoDtoConverter;
import com.bsuir.diploma.bonup.dto.model.NameDto;
import com.bsuir.diploma.bonup.dto.model.organization.OrganizationUserDto;
import com.bsuir.diploma.bonup.dto.model.organization.OrganizationWithPhotoDto;
import com.bsuir.diploma.bonup.dto.model.organization.SetCityOrganization;
import com.bsuir.diploma.bonup.dto.model.organization.SetDescriptionOrganization;
import com.bsuir.diploma.bonup.dto.model.organization.SetXYOrganization;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.exception.organization.NoSuchOrganizationException;
import com.bsuir.diploma.bonup.exception.translation.NoSuchLanguageException;
import com.bsuir.diploma.bonup.exception.user.auth.AccessErrorException;
import com.bsuir.diploma.bonup.exception.validation.NullValidationException;
import com.bsuir.diploma.bonup.model.organization.Organization;
import com.bsuir.diploma.bonup.model.translation.Language;
import com.bsuir.diploma.bonup.model.translation.LanguageKey;
import com.bsuir.diploma.bonup.model.translation.LanguageTranslation;
import com.bsuir.diploma.bonup.model.user.UserLogin;
import com.bsuir.diploma.bonup.model.user.UserRole;
import com.bsuir.diploma.bonup.service.additional.CityService;
import com.bsuir.diploma.bonup.service.organization.OrganizationService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import com.bsuir.diploma.bonup.service.user.UserService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TranslationService translationService;

    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private LanguageKeyRepository languageKeyRepository;
    @Autowired
    private LanguageTranslationRepository languageTranslationRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationToOrganizationWithPhotoDtoConverter organizationToOrganizationWithPhotoDtoConverter;
    @Autowired
    private OrganizationToOrganizationUserDtoConverter organizationToOrganizationUserDtoConverter;

    @Override
    public void checkAdminOrganizationPermission(UserLogin user, String lang) {
        if (!userService.getUserRole(user).equals(UserRole.ROLE_ORGANIZATION_ADMIN))
            throw new AccessErrorException(lang);
    }

    @Override
    public Organization findByNameAndUser(String name, UserLogin userLogin, String lang) {
        return organizationRepository.findByNameAndUserLogin(name, userLogin)
                .orElseThrow(() -> new NoSuchOrganizationException(lang));
    }

    @Override
    public List<Organization> findByUser(UserLogin userLogin) {
        return organizationRepository.findByUserLogin(userLogin);
    }

    @Override
    public int getNumberOfOrganizations(TokenDto tokenDto, String lang) {
        validateTokenUser(tokenDto, lang);
        UserLogin user = userService.findByToken(tokenDto.getToken(), lang);
        checkAdminOrganizationPermission(user, lang);
        return findByUser(user).size();
    }

    @Override
    public List<OrganizationWithPhotoDto> getOrganizations(TokenDto tokenUser, String lang) {
        validateTokenUser(tokenUser, lang);
        UserLogin user = userService.findByToken(tokenUser.getToken(), lang);
        checkAdminOrganizationPermission(user, lang);
        return findByUser(user).stream()
                .map(o -> {
                    OrganizationWithPhotoDto oDto = organizationToOrganizationWithPhotoDtoConverter.convert(o);
                    oDto.setCity(translationService.getMessage(o.getCity().getLanguageKey(), lang));
                    oDto.setCountry(translationService.getMessage(o.getCity().getCountry().getLanguageKey(), lang));
                    return oDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationUserDto getOrganization(NameDto nameDto, String lang) {
        validateTokenNameOrganization(nameDto, lang);
        Organization organization = organizationRepository.findByName(nameDto.getName())
                .orElseThrow(() -> new NoSuchOrganizationException(lang));
        OrganizationUserDto organizationUserDto = organizationToOrganizationUserDtoConverter.convert(organization);
        organizationUserDto.setCity(translationService.getMessage(organization.getCity().getLanguageKey(), lang));
        organizationUserDto.setCountry(translationService.getMessage(organization.getCity().getCountry().getLanguageKey(), lang));
        organizationUserDto.setDescription(translationService.getMessage(organization.getDescriptionKey(), lang));
        return organizationUserDto;
    }

    @Override
    public void setXY(SetXYOrganization setXYOrganization, String lang) {
        Organization organization = validateSetXYOrganization(setXYOrganization, lang);
        organization.setXCoor(setXYOrganization.getX());
        organization.setYCoor(setXYOrganization.getY());
    }

    @Override
    public void setCity(SetCityOrganization setCityOrganization, String lang) {
        Organization organization = validateSetCityOrganization(setCityOrganization, lang);
        organization.setCity(cityService.get(setCityOrganization.getCityId(), lang));
    }

    @Override
    public void setDescription(SetDescriptionOrganization setDescriptionOrganization, String lang) {
        Organization organization = validateSetDescriptionOrganization(setDescriptionOrganization, lang);
        String descriptionKey = "organization.description." + setDescriptionOrganization.getName();
        organization.setDescriptionKey(descriptionKey);

        Language language = languageRepository.findByLang(setDescriptionOrganization.getLangKey())
                .orElseThrow(() -> new NoSuchLanguageException(lang));

        LanguageKey languageKey = languageKeyRepository.findByKey(descriptionKey).orElse(null);
        if (languageKey == null) {
            languageKey = new LanguageKey(descriptionKey);
            languageKeyRepository.save(languageKey);
        }

        LanguageTranslation languageTranslation = languageTranslationRepository.findByLanguageAndLanguageKey(language, languageKey)
                .orElse(null);
        if (languageTranslation == null) {
            languageTranslation = new LanguageTranslation(languageKey, language, setDescriptionOrganization.getDescription());
            languageTranslationRepository.save(languageTranslation);
        } else {
            languageTranslation.setValue(setDescriptionOrganization.getDescription());
        }
    }

    private Organization validateSetXYOrganization(SetXYOrganization setXYOrganization, String lang) {
        if (Objects.isNull(setXYOrganization) || Objects.isNull(setXYOrganization.getToken()) || Objects.isNull(setXYOrganization.getX()) || Objects.isNull(setXYOrganization.getY()) || Objects.isNull(setXYOrganization.getName())) {
            throw new NullValidationException(lang);
        }
        UserLogin user = userService.findByToken(setXYOrganization.getToken(), lang);
        if (!userService.getUserRole(user).equals(UserRole.ROLE_ORGANIZATION_ADMIN))
            throw new AccessErrorException(lang);
        if (Objects.nonNull(setXYOrganization.getName())) {
            return findByNameAndUser(setXYOrganization.getName(), user, lang);
        }
        throw new NullValidationException(lang);
    }

    private Organization validateSetCityOrganization(SetCityOrganization setCityOrganization, String lang) {
        if (Objects.isNull(setCityOrganization) || Objects.isNull(setCityOrganization.getToken()) || Objects.isNull(setCityOrganization.getCityId()) || Objects.isNull(setCityOrganization.getName())) {
            throw new NullValidationException(lang);
        }
        UserLogin user = userService.findByToken(setCityOrganization.getToken(), lang);
        if (!userService.getUserRole(user).equals(UserRole.ROLE_ORGANIZATION_ADMIN))
            throw new AccessErrorException(lang);
        if (Objects.nonNull(setCityOrganization.getName())) {
            return findByNameAndUser(setCityOrganization.getName(), user, lang);
        }
        throw new NullValidationException(lang);
    }

    private Organization validateSetDescriptionOrganization(SetDescriptionOrganization setDescriptionOrganization, String lang) {
        if (Objects.isNull(setDescriptionOrganization) || Objects.isNull(setDescriptionOrganization.getToken())
                || Objects.isNull(setDescriptionOrganization.getDescription()) || Objects.isNull(setDescriptionOrganization.getName()) || Objects.isNull(setDescriptionOrganization.getLangKey())) {
            throw new NullValidationException(lang);
        }
        UserLogin user = userService.findByToken(setDescriptionOrganization.getToken(), lang);
        if (!userService.getUserRole(user).equals(UserRole.ROLE_ORGANIZATION_ADMIN))
            throw new AccessErrorException(lang);
        if (Objects.nonNull(setDescriptionOrganization.getName())) {
            return findByNameAndUser(setDescriptionOrganization.getName(), user, lang);
        }
        throw new NullValidationException(lang);
    }

    private void validateTokenUser(TokenDto tokenDto, String lang) {
        if (Objects.isNull(tokenDto) || Objects.isNull(tokenDto.getToken())) {
            throw new NullValidationException(lang);
        }
    }

    private void validateTokenNameOrganization(NameDto nameDto, String lang) {
        if (Objects.isNull(nameDto) || Objects.isNull(nameDto.getName()))
            throw new NullValidationException(lang);
    }

}
