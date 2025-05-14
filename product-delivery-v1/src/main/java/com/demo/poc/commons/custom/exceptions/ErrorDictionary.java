package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import com.demo.poc.commons.core.errors.exceptions.InvalidFieldException;
import com.demo.poc.commons.core.errors.exceptions.NoSuchParamMapperException;
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
  NO_SUCH_PARAM_MAPPER("01.00.02", "No such param mapper", INTERNAL_SERVER_ERROR, NoSuchParamMapperException.class),

  //others=01
  ERROR_PARSING_JSON("01.01.07", "Error parsing JSON", INTERNAL_SERVER_ERROR, ParsingJsonException.class),

  //state pattern=02
  INVALID_CURRENT_STATE("01.02.01", "Invalid current state", BAD_REQUEST, InvalidCurrentStateException.class),
  NO_SUCH_STATE_HANDLER("01.02.02", "No such state handler", INTERNAL_SERVER_ERROR, NoSuchStateHandlerException.class),
  NULL_CONTACT_DATA_CONTEXT("01.02.03", "Null contact data context", INTERNAL_SERVER_ERROR, NullContactDataContextException.class),
  NULL_AVAILABLE_DATE_CONTEXT("01.02.04", "Null available date context", INTERNAL_SERVER_ERROR, NullAvailableDatesException.class),

  //contact-data=03
  NO_SUCH_PENDING_ORDER("01.03.01", "No such pending order", NOT_FOUND, NoSuchPendingOrderException.class),
  EMAIL_NOT_FOUND("01.03.02", "No valid email found", NOT_FOUND, EmailNotFoundException.class),
  PHONE_NOT_FOUND("01.03.03", "No valid phone number found", NOT_FOUND, EmailNotFoundException.class),

  //available-dates=04
  NOT_FOUND_AVAILABLE_DATES("01.04.01", "Not found available dates", NOT_FOUND, NotFoundAvailableDatesException.class),

  //reservation=05
  NO_SUCH_TIME_RANGE_ID("01.05.01", "No such time range ID", BAD_REQUEST, NoSuchTimeRangeIdException.class),;

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
