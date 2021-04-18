package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NotEnoughBallsException extends BaseException {

    public NotEnoughBallsException(String lang) {
        super(lang);
        setKey("NotEnoughBallsException");
    }

}
