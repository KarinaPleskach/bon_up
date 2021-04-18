package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class CouponAlreadyActivatedException extends BaseException {

    public CouponAlreadyActivatedException(String lang) {
        super(lang);
        setKey("CouponAlreadyActivatedException");
    }

}
