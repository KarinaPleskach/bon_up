package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class CouponNotUpToDateException extends BaseException {

    public CouponNotUpToDateException(String lang) {
        super(lang);
        setKey("CouponNotUpToDateException");
    }

}
