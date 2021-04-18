package com.bsuir.diploma.bonup.exception.task.additional;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NoSuchTypeException extends BaseException {

    public NoSuchTypeException(String lang) {
        super(lang);
        setKey("NoSuchTypeException");
    }

}