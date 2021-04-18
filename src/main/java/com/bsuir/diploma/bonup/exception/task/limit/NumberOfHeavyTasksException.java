package com.bsuir.diploma.bonup.exception.task.limit;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NumberOfHeavyTasksException extends BaseException {

    public NumberOfHeavyTasksException(String lang) {
        super(lang);
        setKey("NumberOfHeavyTasksException");
    }

}
