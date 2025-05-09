package com.demo.poc.commons.core.interceptor.error;

import java.util.stream.Collectors;

import com.demo.poc.commons.core.constants.Symbol;
import com.demo.poc.commons.core.errors.dto.ErrorDto;
import com.demo.poc.commons.core.logging.ErrorThreadContextInjector;
import com.demo.poc.commons.custom.exceptions.ErrorDictionary;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConstraintErrorInterceptor implements ExceptionMapper<ConstraintViolationException> {

  private final ErrorThreadContextInjector contextInjector;

  @Override
  public Response toResponse(ConstraintViolationException exception) {
    contextInjector.populateFromException(exception);

    ErrorDto error = extractError(exception);
    Response.Status status = Response.Status.BAD_REQUEST;

    return Response.status(status)
        .entity(error)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  private static ErrorDto extractError(ConstraintViolationException exception) {
    String message = exception
        .getConstraintViolations()
        .stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.joining(Symbol.COMMA_WITH_SPACE));

    return ErrorDto.builder()
        .code(ErrorDictionary.INVALID_FIELD.getCode())
        .message(message)
        .build();
  }
}
