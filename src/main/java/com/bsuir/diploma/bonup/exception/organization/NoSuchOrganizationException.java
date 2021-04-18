package com.bsuir.diploma.bonup.exception.organization;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NoSuchOrganizationException extends BaseException {

    public NoSuchOrganizationException(String lang) {
        super(lang);
        setKey("message.organization.not.exist");
    }

}
