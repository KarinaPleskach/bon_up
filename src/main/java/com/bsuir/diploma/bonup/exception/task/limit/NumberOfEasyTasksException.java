package com.bsuir.diploma.bonup.exception.task.limit;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NumberOfEasyTasksException extends BaseException {

    public NumberOfEasyTasksException(String lang) {
        super(lang);
        setKey("NumberOfEasyTasksException");
    }

}
