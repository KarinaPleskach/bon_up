package com.bsuir.diploma.bonup.exception.task.limit;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NumberOfHeavyCouponsException extends BaseException {

    public NumberOfHeavyCouponsException(String lang) {
        super(lang);
        setKey("NumberOfHeavyCouponsException");
    }

}
