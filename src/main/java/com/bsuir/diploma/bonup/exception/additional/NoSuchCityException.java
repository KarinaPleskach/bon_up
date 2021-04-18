package com.bsuir.diploma.bonup.exception.additional;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NoSuchCityException extends BaseException {

    public NoSuchCityException(String lang) {
        super(lang);
        setKey("message.city.not.exist");
    }

}
