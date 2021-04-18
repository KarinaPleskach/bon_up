package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class CouponNotBoughtException extends BaseException {

    public CouponNotBoughtException(String lang) {
        super(lang);
        setKey("CouponNotBoughtException");
    }

}
