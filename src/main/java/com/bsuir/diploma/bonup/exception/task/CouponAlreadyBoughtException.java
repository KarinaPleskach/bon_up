package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class CouponAlreadyBoughtException extends BaseException {

    public CouponAlreadyBoughtException(String lang) {
        super(lang);
        setKey("CouponAlreadyBoughtException");
    }

}
