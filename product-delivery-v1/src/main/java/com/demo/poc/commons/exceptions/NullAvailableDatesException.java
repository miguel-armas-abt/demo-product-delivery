package com.demo.poc.commons.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;

import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

public class NullAvailableDatesException extends GenericException {

    public static final String INVALID_FIELD_CODE = "01.02.04";

    public NullAvailableDatesException() {
        super(
            INVALID_FIELD_CODE,
            "Null available date context",
            INTERNAL_SERVER_ERROR
        );
    }
}
