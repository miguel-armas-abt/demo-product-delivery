package com.demo.services.entrypoint.contactdata.exceptions;

import com.demo.commons.errors.exceptions.GenericException;
import lombok.Getter;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Getter
public class EmailNotFoundException extends GenericException {

    public static final String INVALID_FIELD_CODE = "01.03.02";

    public EmailNotFoundException() {
        super(
            INVALID_FIELD_CODE,
            "No valid email found",
            NOT_FOUND
        );
    }
}
