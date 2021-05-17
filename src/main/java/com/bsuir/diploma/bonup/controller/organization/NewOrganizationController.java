package com.bsuir.diploma.bonup.controller.organization;

import com.bsuir.diploma.bonup.dto.model.user.auth.organization.OrganizationNewDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.service.organization.OrganizationNewService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewOrganizationController {

    @Autowired
    private TranslationService translationService;
    @Autowired
    private OrganizationNewService organizationNewService;

    @PostMapping("/{lang}/newOrganization")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> addOrganization(@PathVariable("lang") String lang, @RequestBody OrganizationNewDto newOrganization) {
        organizationNewService.create(newOrganization, lang);
        String message = translationService.getMessage("message.organization.add", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }
}
