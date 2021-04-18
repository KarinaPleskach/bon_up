package com.bsuir.diploma.bonup.controller.admin.organization;

import com.bsuir.diploma.bonup.dto.model.organization.AllContractDto;
import com.bsuir.diploma.bonup.dto.model.organization.admin.AdminNameDto;
import com.bsuir.diploma.bonup.dto.model.organization.admin.ContractDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.AdminAuthUser;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.NewOrganization;
import com.bsuir.diploma.bonup.dto.model.user.auth.organization.OldAdminAuthUser;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.dto.response.ResponseWithToken;
import com.bsuir.diploma.bonup.dto.response.organization.ResponseWithContracts;
import com.bsuir.diploma.bonup.service.admin.organization.AdminOrganizationService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminOrganizationController {

    @Autowired
    private AdminOrganizationService adminOrganizationService;
    @Autowired
    private TranslationService translationService;

    @PutMapping("/{lang}/registerOrganizationUser")
    @ResponseBody
    public ResponseEntity<ResponseWithToken> registerOrganizationUser(@PathVariable("lang") String lang, @RequestBody AdminAuthUser authUserDto) {
        String token = adminOrganizationService.createNewUser(authUserDto, lang);
        String message = translationService.getMessage("message.register.success", lang);
        return new ResponseEntity<>(new ResponseWithToken(true, message, token), HttpStatus.OK);
    }

    @PostMapping("/{lang}/userOrganizationToken")
    @ResponseBody
    public ResponseEntity<ResponseWithToken> getUserOrganizationToken(@PathVariable("lang") String lang, @RequestBody OldAdminAuthUser oldAdminAuthUser) {
        String token = adminOrganizationService.getUserOrganizationToken(oldAdminAuthUser, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithToken(true, message, token), HttpStatus.OK);
    }

    @PutMapping("/{lang}/addOrganizationForUser")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> addOrganization(@PathVariable("lang") String lang, @RequestBody NewOrganization newOrganization) {
        adminOrganizationService.createOrganizationForUser(newOrganization, lang);
        String message = translationService.getMessage("message.organization.add", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PutMapping("/{lang}/contract")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> addContract(@PathVariable("lang") String lang, @RequestBody ContractDto contractDto) {
        adminOrganizationService.setContract(contractDto, lang);
        String message = translationService.getMessage("message.setted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/contract")
    @ResponseBody
    public ResponseEntity<ResponseWithContracts> getContracts(@PathVariable("lang") String lang, @RequestBody AdminNameDto adminNameDto) {
        List<AllContractDto> list = adminOrganizationService.getContracts(adminNameDto, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithContracts(true, message, list), HttpStatus.OK);
    }

    @PatchMapping("/{lang}/contract/{contractId}")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> updateContract(@PathVariable("lang") String lang, @RequestBody ContractDto contractDto, @PathVariable("contractId") Long contractId) {
        adminOrganizationService.updateContract(contractDto, contractId, lang);
        String message = translationService.getMessage("message.setted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }


}
