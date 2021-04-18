package com.bsuir.diploma.bonup.controller.user;

import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
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

    @PostMapping("/{lang}/userRole")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> getRole(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        String message = userService.getUserRole(tokenUser, lang).name();
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

}
