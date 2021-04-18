package com.bsuir.diploma.bonup.exception.task.additional;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NoSuchCategoryException extends BaseException {

    public NoSuchCategoryException(String lang) {
        super(lang);
        setKey("message.no.such.category");
    }

}

