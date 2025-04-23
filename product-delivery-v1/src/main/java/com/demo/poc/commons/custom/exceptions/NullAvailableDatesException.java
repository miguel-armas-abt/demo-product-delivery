package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class NullAvailableDatesException extends GenericException {

    public NullAvailableDatesException() {
        super(ErrorDictionary.NULL_AVAILABLE_DATE_CONTEXT.getMessage(), ErrorDictionary.parse(NullAvailableDatesException.class));
    }
}
