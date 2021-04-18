package com.bsuir.diploma.bonup.exception.task.additional;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NotSubCategoryException extends BaseException {

    public NotSubCategoryException(String lang) {
        super(lang);
        setKey("message.not.subcategory");
    }

}