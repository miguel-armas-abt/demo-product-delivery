package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import com.demo.poc.commons.custom.states.State;
import lombok.Getter;

@Getter
public class InvalidCurrentStateException extends GenericException {

    private static final String MESSAGE = ErrorDictionary.INVALID_CURRENT_STATE.getMessage();

    public InvalidCurrentStateException(State currentState) {
        super(MESSAGE + ": " + currentState, ErrorDictionary.parse(InvalidCurrentStateException.class));
    }
}
