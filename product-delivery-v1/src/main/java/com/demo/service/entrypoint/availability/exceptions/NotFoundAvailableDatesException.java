package com.demo.service.entrypoint.availability.exceptions;

import com.demo.commons.errors.exceptions.GenericException;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

public class NotFoundAvailableDatesException extends GenericException {

    public static final String INVALID_FIELD_CODE = "01.04.01";

    public NotFoundAvailableDatesException() {
        super(
            INVALID_FIELD_CODE,
            "Not found available dates",
            NOT_FOUND
        );
    }
}
