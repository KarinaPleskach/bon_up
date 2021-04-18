package com.bsuir.diploma.bonup.exception.validation;

import com.bsuir.diploma.bonup.exception.BaseException;

public class DataNotFoundException extends BaseException {

    public DataNotFoundException(String lang) {
        super(lang);
        setKey("message.data.notfound");
    }

}
