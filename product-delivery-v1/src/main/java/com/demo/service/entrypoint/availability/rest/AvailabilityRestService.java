package com.demo.service.entrypoint.availability.rest;

import java.util.Map;

import com.demo.commons.restserver.RestServerUtils;
import com.demo.commons.validations.ParamValidator;
import com.demo.commons.validations.headers.DefaultHeaders;
import com.demo.service.entrypoint.availability.dto.request.AvailabilityRequestDto;
import com.demo.service.entrypoint.availability.dto.response.AvailabilityResponseDto;
import com.demo.service.entrypoint.availability.service.AvailabilityService;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/poc/product-delivery/v1/available-dates")
@Produces({MediaType.APPLICATION_JSON, "application/x-ndjson"})
@Consumes({MediaType.APPLICATION_JSON, "application/x-ndjson"})
@RequiredArgsConstructor
public class AvailabilityRestService {

  private final AvailabilityService availabilityService;
  private final ParamValidator paramValidator;

  @Context
  HttpHeaders httpHeaders;

  @POST
  public Uni<AvailabilityResponseDto> getAvailableDates(AvailabilityRequestDto request) {
    Map<String, String> headers = RestServerUtils.extractHeadersAsMap(httpHeaders.getRequestHeaders());
    return paramValidator.validateAndGet(headers, DefaultHeaders.class)
        .flatMap(defaultHeaders -> availabilityService.getAvailableDates(request));
  }
}
