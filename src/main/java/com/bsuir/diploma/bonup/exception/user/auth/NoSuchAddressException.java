package com.bsuir.diploma.bonup.exception.user.auth;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NoSuchAddressException extends BaseException {

    public NoSuchAddressException(String lang) {
        super(lang);
        setKey("message.incorrect.mail.address");
    }

}
