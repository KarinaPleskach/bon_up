package com.bsuir.diploma.bonup.controller.organization;

import com.bsuir.diploma.bonup.dto.model.NameDto;
import com.bsuir.diploma.bonup.dto.model.organization.OrganizationPhotoDto;
import com.bsuir.diploma.bonup.dto.model.organization.OrganizationUserDto;
import com.bsuir.diploma.bonup.dto.model.organization.OrganizationWithPhotoDto;
import com.bsuir.diploma.bonup.dto.model.organization.SetCityOrganization;
import com.bsuir.diploma.bonup.dto.model.organization.SetDescriptionOrganization;
import com.bsuir.diploma.bonup.dto.model.organization.SetXYOrganization;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.dto.response.organization.ResponseWithOrganization;
import com.bsuir.diploma.bonup.dto.response.organization.ResponseWithOrganizations;
import com.bsuir.diploma.bonup.service.organization.OrganizationService;
import com.bsuir.diploma.bonup.service.photo.OrganizationPhotoService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private TranslationService translationService;
    @Autowired
    private OrganizationPhotoService organizationPhotoService;

    @PatchMapping("/{lang}/setXY")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> setXY(@PathVariable("lang") String lang, @RequestBody SetXYOrganization setXYOrganization) {
        organizationService.setXY(setXYOrganization, lang);
        String message = translationService.getMessage("message.setted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PatchMapping("/{lang}/setCity")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> setCity(@PathVariable("lang") String lang, @RequestBody SetCityOrganization setCityOrganization) {
        organizationService.setCity(setCityOrganization, lang);
        String message = translationService.getMessage("message.setted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PatchMapping("/{lang}/setDescription")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> setDescriptionOrganization(@PathVariable("lang") String lang, @RequestBody SetDescriptionOrganization setDescriptionOrganization) {
        organizationService.setDescription(setDescriptionOrganization, lang);
        String message = translationService.getMessage("message.setted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PutMapping("/{lang}/organizationPhoto")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> saveOrganizationPhoto(@PathVariable("lang") String lang, @RequestBody OrganizationPhotoDto organizationPhotoDto) {
        organizationPhotoService.saveOrganizationPhoto(organizationPhotoDto, lang);
        String message = translationService.getMessage("message.setted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @DeleteMapping("/{lang}/organizationPhoto")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> deleteOrganizationPhoto(@PathVariable("lang") String lang, @RequestBody OrganizationPhotoDto organizationPhotoDto) {
        organizationPhotoService.deleteOrganizationPhoto(organizationPhotoDto, lang);
        String message = translationService.getMessage("message.deleted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfOrganizations")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> getNumberOfOrganizations(@PathVariable("lang") String lang, @RequestBody TokenDto tokenDto) {
        String message = String.valueOf(organizationService.getNumberOfOrganizations(tokenDto, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/organizations")
    @ResponseBody
    public ResponseEntity<ResponseWithOrganizations> getListOfOrganizations(@PathVariable("lang") String lang, @RequestBody TokenDto tokenDto) {
        List<OrganizationWithPhotoDto> list = organizationService.getOrganizations(tokenDto, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithOrganizations(true, message, list), HttpStatus.OK);
    }

    @PostMapping("/{lang}/organization")
    @ResponseBody
    public ResponseEntity<ResponseWithOrganization> getOrganizationDto(@PathVariable("lang") String lang, @RequestBody NameDto nameDto) {
        OrganizationUserDto organizationDto = organizationService.getOrganization(nameDto, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithOrganization(true, message, organizationDto), HttpStatus.OK);
    }

}
