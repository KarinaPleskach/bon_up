package com.bsuir.diploma.bonup.exception.user.auth;

import com.bsuir.diploma.bonup.exception.BaseException;

public class UserAlreadyExistException extends BaseException {

    public UserAlreadyExistException(String lang) {
        super(lang);
        setKey("message.userExist");
    }

}
