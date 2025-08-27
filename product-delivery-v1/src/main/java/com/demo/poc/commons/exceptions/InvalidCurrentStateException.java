package com.demo.poc.commons.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import com.demo.poc.commons.states.State;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

public class InvalidCurrentStateException extends GenericException {

    public static final String INVALID_FIELD_CODE = "01.02.01";

    public InvalidCurrentStateException(State currentState) {
        super(
            INVALID_FIELD_CODE,
            "Invalid current state: " + currentState,
            BAD_REQUEST
        );
    }
}
