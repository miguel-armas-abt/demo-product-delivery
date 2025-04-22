package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class NoSuchPendingOrderException extends GenericException {

    public NoSuchPendingOrderException(String pendingOrderId) {
        super(ErrorDictionary.NO_SUCH_PENDING_ORDER.getMessage() + " " + pendingOrderId, ErrorDictionary.parse(NoSuchPendingOrderException.class));
    }
}
