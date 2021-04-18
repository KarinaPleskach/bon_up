package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class CouponNotComeException extends BaseException {

    public CouponNotComeException(String lang) {
        super(lang);
        setKey("CouponNotComeException");
    }

}
