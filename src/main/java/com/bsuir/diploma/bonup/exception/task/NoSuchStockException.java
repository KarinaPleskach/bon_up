package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NoSuchStockException extends BaseException {

    public NoSuchStockException(String lang) {
        super(lang);
        setKey("NoSuchStockException");
    }

}

