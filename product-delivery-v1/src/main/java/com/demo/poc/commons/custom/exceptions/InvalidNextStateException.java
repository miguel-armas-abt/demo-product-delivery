package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class InvalidNextStateException extends GenericException {

    public InvalidNextStateException(String nextState) {
        super(ErrorDictionary.INVALID_NEXT_STATE.getMessage() + " " + nextState, ErrorDictionary.parse(InvalidNextStateException.class));
    }
}
