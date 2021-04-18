package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class TaskAlreadyRejectedException extends BaseException {

    public TaskAlreadyRejectedException(String lang) {
        super(lang);
        setKey("TaskAlreadyRejectedException");
    }

}
