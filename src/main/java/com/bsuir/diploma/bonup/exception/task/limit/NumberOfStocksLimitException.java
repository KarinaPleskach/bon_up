package com.bsuir.diploma.bonup.exception.task.limit;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NumberOfStocksLimitException extends BaseException {

    public NumberOfStocksLimitException(String lang) {
        super(lang);
        setKey("NumberOfStocksLimitException");
    }

}
