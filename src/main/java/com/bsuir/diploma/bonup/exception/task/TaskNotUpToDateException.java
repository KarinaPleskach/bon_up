package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class TaskNotUpToDateException extends BaseException {

    public TaskNotUpToDateException(String lang) {
        super(lang);
        setKey("TaskNotUpToDateException");
    }

}
