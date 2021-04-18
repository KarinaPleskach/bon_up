package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class TaskAlreadyDoneException extends BaseException {

    public TaskAlreadyDoneException(String lang) {
        super(lang);
        setKey("TaskAlreadyDoneException");
    }

}
