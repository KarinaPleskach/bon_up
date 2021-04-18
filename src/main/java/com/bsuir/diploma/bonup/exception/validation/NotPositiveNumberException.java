package com.bsuir.diploma.bonup.exception.validation;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NotPositiveNumberException extends BaseException {

    public NotPositiveNumberException(String lang) {
        super(lang);
        setKey("NotPositiveNumberException");
    }

}
