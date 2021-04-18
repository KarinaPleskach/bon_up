package com.bsuir.diploma.bonup.exception.user.auth;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NoSuchUserException extends BaseException {

    public NoSuchUserException(String lang) {
        super(lang);
        setKey("message.noUserExist");
    }

}
