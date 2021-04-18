package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NumberOfCouponsLimitException extends BaseException {

    public NumberOfCouponsLimitException(String lang) {
        super(lang);
        setKey("NumberOfCouponsLimitException");
    }

}
