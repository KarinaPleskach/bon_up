package com.bsuir.diploma.bonup.exception.user.auth;

import com.bsuir.diploma.bonup.exception.BaseException;

public class IncorrectLoginDataException extends BaseException {

    public IncorrectLoginDataException(String lang) {
        super(lang);
        setKey("message.incorrect.login");
    }

}
