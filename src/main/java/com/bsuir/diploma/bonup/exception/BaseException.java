package com.bsuir.diploma.bonup.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseException extends RuntimeException {

    private String key;
    private String lang;

    public BaseException(String lang) {
        super();
        this.lang = lang;
    }

    public BaseException() { }

}
