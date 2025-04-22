package com.demo.poc.commons.core.errors.exceptions;

import com.demo.poc.commons.core.errors.dto.ErrorDto;
import jakarta.ws.rs.core.Response.Status;
import lombok.Getter;

@Getter
public class RestClientException extends RuntimeException {

  private final ErrorDto errorDetail;
  private final Status httpStatusCode;

  public RestClientException(String code, String message, Status httpStatusCode) {
    super(message);
    this.errorDetail = ErrorDto.builder()
        .code(code)
        .message(message)
        .build();
    this.httpStatusCode = httpStatusCode;
  }
}
