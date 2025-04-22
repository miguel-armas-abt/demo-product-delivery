package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class EmailNotFoundException extends GenericException {

    public EmailNotFoundException() {
        super(ErrorDictionary.EMAIL_NOT_FOUND.getMessage(), ErrorDictionary.parse(EmailNotFoundException.class));
    }
}
