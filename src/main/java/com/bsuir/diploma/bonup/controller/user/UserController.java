package com.bsuir.diploma.bonup.controller.user;

import com.bsuir.diploma.bonup.dto.converter.user.UserInfoDto;
import com.bsuir.diploma.bonup.dto.model.TokenIdsDro;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.dto.response.user.ResponseWithUser;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import com.bsuir.diploma.bonup.service.user.ProfileService;
import com.bsuir.diploma.bonup.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    ProfileService profileService;
    @Autowired
    private TranslationService translationService;

    @PostMapping("/{lang}/setCategories")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> setCategories(@PathVariable("lang") String lang, @RequestBody TokenIdsDro tokenIdsDro) {
        profileService.setCategories(tokenIdsDro, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/userRole")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> getRole(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        String message = userService.getUserRole(tokenUser, lang).name();
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/userInfo")
    @ResponseBody
    public ResponseEntity<ResponseWithUser> userInfo(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        UserInfoDto userInfoDto = userService.userInfo(tokenUser, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithUser(true, message, userInfoDto), HttpStatus.OK);
    }

}
