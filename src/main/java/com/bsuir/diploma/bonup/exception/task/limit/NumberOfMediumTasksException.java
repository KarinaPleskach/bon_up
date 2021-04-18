package com.bsuir.diploma.bonup.exception.task.limit;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NumberOfMediumTasksException extends BaseException {

    public NumberOfMediumTasksException(String lang) {
        super(lang);
        setKey("NumberOfMediumTasksException");
    }

}
