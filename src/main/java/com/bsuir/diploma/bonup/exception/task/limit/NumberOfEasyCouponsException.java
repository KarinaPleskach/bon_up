package com.bsuir.diploma.bonup.exception.task.limit;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NumberOfEasyCouponsException extends BaseException {

    public NumberOfEasyCouponsException(String lang) {
        super(lang);
        setKey("NumberOfEasyCouponsException");
    }

}
