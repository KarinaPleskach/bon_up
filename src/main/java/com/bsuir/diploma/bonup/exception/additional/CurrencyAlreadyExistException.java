package com.bsuir.diploma.bonup.exception.additional;

import com.bsuir.diploma.bonup.exception.BaseException;

public class CurrencyAlreadyExistException extends BaseException {

    public CurrencyAlreadyExistException(String lang) {
        super(lang);
        setKey("message.currency.exist");
    }

}
