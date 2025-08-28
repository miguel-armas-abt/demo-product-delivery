package com.demo.service.entrypoint.reservation.exceptions;

import com.demo.commons.errors.exceptions.GenericException;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

public class NoSuchTimeRangeIdException extends GenericException {

    public static final String INVALID_FIELD_CODE = "01.05.01";

    public NoSuchTimeRangeIdException(String timeRangeId) {
        super(
            INVALID_FIELD_CODE,
            "No such time range ID: " + timeRangeId,
            BAD_REQUEST
        );
    }
}
