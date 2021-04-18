package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class TaskAlreadyAcceptedException extends BaseException {

    public TaskAlreadyAcceptedException(String lang) {
        super(lang);
        setKey("TaskAlreadyAcceptedException");
    }

}
