package com.demo.poc.commons.core.interceptor.error;

import java.util.stream.Collectors;

import com.demo.poc.commons.core.errors.dto.ErrorDto;
import com.demo.poc.commons.core.logging.ThreadContextInjector;
import com.demo.poc.commons.custom.exceptions.ErrorDictionary;
import com.demo.poc.commons.custom.properties.ApplicationProperties;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConstraintErrorInterceptor implements ExceptionMapper<ConstraintViolationException> {

  private final ApplicationProperties properties;
  private final ThreadContextInjector threadContextInjector;

  @Override
  public Response toResponse(ConstraintViolationException exception) {
    generateTrace(exception);

    ErrorDto error = extractError(exception);
    Response.Status status = Response.Status.BAD_REQUEST;

    return Response.status(status)
        .entity(error)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  private void generateTrace(Throwable throwable) {
    threadContextInjector.populateFromException(throwable);
  }

  private static ErrorDto extractError(ConstraintViolationException exception) {
    String message = exception
        .getConstraintViolations()
        .stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.joining(";"));

    return ErrorDto.builder()
        .code(ErrorDictionary.INVALID_FIELD.getCode())
        .message(message)
        .build();
  }
}
