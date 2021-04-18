package com.bsuir.diploma.bonup.controller.user.auth;

import com.bsuir.diploma.bonup.dto.model.user.auth.EmailCodeDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.EmailDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.NewPasswordDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.RegUserDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.dto.response.ResponseWithToken;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import com.bsuir.diploma.bonup.service.user.auth.RegistrationService;
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
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private TranslationService translationService;

    @PutMapping(value = "/{lang}/registration")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> createNewUser(@PathVariable("lang") String lang, @RequestBody RegUserDto user) {
        registrationService.createNewUser(user, lang);
        String message = translationService.getMessage("message.sendCode", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/verifyMail")
    @ResponseBody
    public ResponseEntity<ResponseWithToken> verifyMail(@PathVariable("lang") String lang, @RequestBody EmailCodeDto emailCodeDto) {
        registrationService.checkMailCode(emailCodeDto, lang);
        String token = registrationService.generateNewToken(emailCodeDto.getEmail(), lang);
        String message = translationService.getMessage("message.valid.email.code", lang);
        return new ResponseEntity<>(new ResponseWithToken(true, message, token), HttpStatus.OK);
    }

    @PostMapping("/{lang}/isUserExist")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> resetPassword(@PathVariable("lang") String lang, @RequestBody EmailDto emailDto) {
        registrationService.resendCodeAndNullToken(emailDto, lang);
        String message = translationService.getMessage("message.sendCode", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PatchMapping("/{lang}/newPassword")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> newPassword(@PathVariable("lang") String lang, @RequestBody NewPasswordDto newPassword) {
        registrationService.newPassword(newPassword, lang);
        registrationService.generateNewTokenWithToken(newPassword.getToken(), lang);
        String message = translationService.getMessage("message.password.changed", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/login")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> login(@PathVariable("lang") String lang, @RequestBody RegUserDto authUserDto) {
        String token = registrationService.login(authUserDto, lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, token), HttpStatus.OK);
    }

}
