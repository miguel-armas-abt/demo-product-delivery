package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class InvalidCurrentStateException extends GenericException {

    public InvalidCurrentStateException() {
        super(ErrorDictionary.INVALID_CURRENT_STATE.getMessage(), ErrorDictionary.parse(InvalidCurrentStateException.class));
    }
}
