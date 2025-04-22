package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class InvalidPreviousStateException extends GenericException {

    public InvalidPreviousStateException(String previousState) {
        super(ErrorDictionary.INVALID_PREVIOUS_STATE.getMessage() + " " + previousState, ErrorDictionary.parse(InvalidPreviousStateException.class));
    }
}
