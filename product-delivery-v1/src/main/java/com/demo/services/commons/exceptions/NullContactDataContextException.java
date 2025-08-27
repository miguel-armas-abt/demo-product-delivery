package com.demo.services.commons.exceptions;

import com.demo.commons.errors.exceptions.GenericException;

import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

public class NullContactDataContextException extends GenericException {

    public static final String INVALID_FIELD_CODE = "01.02.03";

    public NullContactDataContextException() {
        super(
            INVALID_FIELD_CODE,
            "Null contact data context",
            INTERNAL_SERVER_ERROR
        );
    }
}
