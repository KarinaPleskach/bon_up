package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NumberOfTasksLimitException extends BaseException {

    public NumberOfTasksLimitException(String lang) {
        super(lang);
        setKey("NumberOfTasksLimitException");
    }

}
