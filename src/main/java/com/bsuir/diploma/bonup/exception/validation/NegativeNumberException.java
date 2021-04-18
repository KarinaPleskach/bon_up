package com.bsuir.diploma.bonup.exception.validation;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NegativeNumberException extends BaseException {

    public NegativeNumberException(String lang) {
        super(lang);
        setKey("NegativaNumberException");
    }

}
