package com.bsuir.diploma.bonup.exception.validation;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NullValidationException  extends BaseException {

    public NullValidationException(String lang) {
        super(lang);
        setKey("message.nullValue");
    }

}
