package com.demo.poc.commons.core.interceptor.error;

import com.demo.poc.commons.core.errors.dto.ErrorDto;
import com.demo.poc.commons.core.errors.exceptions.GenericException;
import com.demo.poc.commons.core.logging.ErrorThreadContextInjector;
import com.demo.poc.commons.core.logging.enums.LoggingType;
import com.demo.poc.commons.custom.properties.ApplicationProperties;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@RequiredArgsConstructor
public class ErrorInterceptor implements ExceptionMapper<Throwable> {

  private final ApplicationProperties properties;
  private final ErrorThreadContextInjector contextInjector;

  @Override
  public Response toResponse(Throwable throwable) {
    boolean isLoggerPresent = LoggingType.isLoggerPresent(properties, LoggingType.ERROR);
    if (isLoggerPresent) {
      contextInjector.populateFromException(throwable);
    }

    ErrorDto error = ErrorDto.getDefaultError(properties);
    Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

    if (throwable instanceof WebApplicationException webApplicationException) {
      status = Response.Status.fromStatusCode(webApplicationException.getResponse().getStatus());
    }

    if (throwable instanceof ProcessingException) {
      status = Response.Status.REQUEST_TIMEOUT;
    }

    if (throwable instanceof GenericException genericException) {
      error = genericException.getErrorDetail();
      status = genericException.getHttpStatus();
    }

    return Response.status(status)
        .entity(error)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}