package com.bsuir.diploma.bonup.exception.user.auth;

import com.bsuir.diploma.bonup.exception.BaseException;

public class AccessErrorException extends BaseException {

    public AccessErrorException(String lang) {
        super(lang);
        setKey("message.access.error");
    }

}
