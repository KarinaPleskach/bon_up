package com.bsuir.diploma.bonup.exception.organization;

import com.bsuir.diploma.bonup.exception.BaseException;

public class OrganizationWithSuchNameAlreadyExistException extends BaseException {

    public OrganizationWithSuchNameAlreadyExistException(String lang) {
        super(lang);
        setKey("message.organization.name.exist");
    }

}
