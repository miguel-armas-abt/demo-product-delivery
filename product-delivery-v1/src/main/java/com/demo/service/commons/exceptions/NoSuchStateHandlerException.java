package com.demo.service.commons.exceptions;

import com.demo.commons.errors.exceptions.GenericException;
import com.demo.service.commons.states.State;

import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

public class NoSuchStateHandlerException extends GenericException {

  public static final String INVALID_FIELD_CODE = "01.02.02";

  public NoSuchStateHandlerException(State state) {
    super(
        INVALID_FIELD_CODE,
        "No such state handler: " + state,
        INTERNAL_SERVER_ERROR
    );
  }
}
