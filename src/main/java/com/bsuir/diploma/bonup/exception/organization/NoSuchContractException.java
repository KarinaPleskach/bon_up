package com.bsuir.diploma.bonup.exception.organization;

import com.bsuir.diploma.bonup.exception.BaseException;

public class NoSuchContractException extends BaseException {

    public NoSuchContractException(String lang) {
        super(lang);
        setKey("message.contract.not.exist");
    }

}
