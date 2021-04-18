package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class TaskNotComeException extends BaseException {

    public TaskNotComeException(String lang) {
        super(lang);
        setKey("TaskNotComeException");
    }

}
