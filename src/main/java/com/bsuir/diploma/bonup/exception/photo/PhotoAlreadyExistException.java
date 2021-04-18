package com.bsuir.diploma.bonup.exception.photo;

import com.bsuir.diploma.bonup.exception.BaseException;

public class PhotoAlreadyExistException extends BaseException {

    public PhotoAlreadyExistException(String lang) {
        super(lang);
        setKey("message.photo.exist");
    }

}
