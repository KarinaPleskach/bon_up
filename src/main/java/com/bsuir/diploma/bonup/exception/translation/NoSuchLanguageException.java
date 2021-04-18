package com.bsuir.diploma.bonup.exception.translation;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NoSuchLanguageException extends BaseException {

    public NoSuchLanguageException(String lang) {
        super(lang);
        setKey("message.noSuchLanguage");
    }

}
