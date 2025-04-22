package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import com.demo.poc.commons.core.errors.exceptions.InvalidFieldException;
import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;
import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum ErrorDictionary {

  //system=00
  INVALID_FIELD("01.00.01", "Invalid field", BAD_REQUEST, InvalidFieldException.class),

  //custom=01
  NO_SUCH_PENDING_ORDER("01.01.01", "No such pending order", NOT_FOUND, NoSuchPendingOrderException.class),
  EMAIL_NOT_FOUND("01.01.02", "No valid email found", NOT_FOUND, EmailNotFoundException.class),
  PHONE_NOT_FOUND("01.01.03", "No valid phone number found", NOT_FOUND, EmailNotFoundException.class),
  INVALID_PREVIOUS_STATE("01.01.04", "Invalid previous state", BAD_REQUEST, InvalidPreviousStateException.class),
  INVALID_NEXT_STATE("01.01.05", "Invalid next state", BAD_REQUEST, InvalidPreviousStateException.class),
  INVALID_CURRENT_STATE("01.01.06", "Invalid current state", INTERNAL_SERVER_ERROR, InvalidCurrentStateException.class),
  ERROR_PARSING_JSON("01.01.07", "Error parsing JSON", INTERNAL_SERVER_ERROR, ParsingJsonException.class),
  NOT_FOUND_AVAILABLE_DATES("01.01.08", "Not found available dates", NOT_FOUND, NotFoundAvailableDatesException.class),;

  private final String code;
  private final String message;
  private final Response.Status httpStatus;
  private final Class<? extends GenericException> exceptionClass;

  public static ErrorDictionary parse(Class<? extends GenericException> exceptionClass) {
    return Arrays.stream(ErrorDictionary.values())
            .filter(errorDetail -> errorDetail.getExceptionClass().isAssignableFrom(exceptionClass))
            .findFirst()
            .orElseThrow(() -> new GenericException("No such exception"));
  }
}
