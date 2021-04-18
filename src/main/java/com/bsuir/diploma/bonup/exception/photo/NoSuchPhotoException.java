package com.bsuir.diploma.bonup.exception.photo;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NoSuchPhotoException extends BaseException {

    public NoSuchPhotoException(String lang) {
        super(lang);
        setKey("message.photo.not.exist");
    }

}
