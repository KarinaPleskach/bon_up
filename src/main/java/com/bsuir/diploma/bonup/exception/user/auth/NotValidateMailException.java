package com.bsuir.diploma.bonup.exception.user.auth;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NotValidateMailException extends BaseException {

    public NotValidateMailException(String lang) {
        super(lang);
        setKey("message.not.validate.mail");
    }

}
