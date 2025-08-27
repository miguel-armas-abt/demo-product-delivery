package com.demo.poc.entrypoint.contactdata.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

public class PhoneNotFoundException extends GenericException {

    public static final String INVALID_FIELD_CODE = "01.03.03";

    public PhoneNotFoundException() {
        super(
            INVALID_FIELD_CODE,
            "No valid phone number found",
            NOT_FOUND
        );
    }
}
