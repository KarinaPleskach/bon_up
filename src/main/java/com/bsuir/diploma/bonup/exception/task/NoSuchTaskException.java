package com.bsuir.diploma.bonup.exception.task;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NoSuchTaskException extends BaseException {

    public NoSuchTaskException(String lang) {
        super(lang);
        setKey("NoSuchTaskException");
    }

}
