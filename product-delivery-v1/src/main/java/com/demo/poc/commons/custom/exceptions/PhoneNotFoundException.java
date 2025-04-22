package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class PhoneNotFoundException extends GenericException {

    public PhoneNotFoundException() {
        super(ErrorDictionary.PHONE_NOT_FOUND.getMessage(), ErrorDictionary.parse(PhoneNotFoundException.class));
    }
}
