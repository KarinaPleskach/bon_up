package com.bsuir.diploma.bonup.exception.validation;

import com.bsuir.diploma.bonup.exception.BaseException;

public class DateToBeforeException extends BaseException {

    public DateToBeforeException(String lang) {
        super(lang);
        setKey("message.dateTo.before");
    }

}
