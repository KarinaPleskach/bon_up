package com.bsuir.diploma.bonup.exception.user.auth;

import com.bsuir.diploma.bonup.exception.BaseException;

public class InvalidEmailCodeException extends BaseException {

    public InvalidEmailCodeException(String lang) {
        super(lang);
        setKey("message.invalid.email.code");
    }

}
