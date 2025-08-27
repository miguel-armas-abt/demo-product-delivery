package com.demo.services.entrypoint.contactdata.exceptions;

import com.demo.commons.errors.exceptions.GenericException;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

public class NoSuchPendingOrderException extends GenericException {

    public static final String INVALID_FIELD_CODE = "01.03.01";

    public NoSuchPendingOrderException(String pendingOrderId) {
        super(
            INVALID_FIELD_CODE,
            "No such pending order" + pendingOrderId,
            NOT_FOUND
        );
    }
}
