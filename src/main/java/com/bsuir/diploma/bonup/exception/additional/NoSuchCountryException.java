package com.bsuir.diploma.bonup.exception.additional;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NoSuchCountryException extends BaseException {

    public NoSuchCountryException(String lang) {
        super(lang);
        setKey("message.country.not.exist");
    }

}
