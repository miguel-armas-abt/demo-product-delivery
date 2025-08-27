package com.demo.services.entrypoint.reservation.rest;

import java.util.Map;

import com.demo.commons.restserver.RestServerUtils;
import com.demo.commons.validations.ParamValidator;
import com.demo.commons.validations.headers.DefaultHeaders;
import com.demo.services.entrypoint.reservation.dto.request.ReservationRequestDto;
import com.demo.services.entrypoint.reservation.dto.response.ReservationResponseDto;
import com.demo.services.entrypoint.reservation.service.ReservationService;
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
@Path("/poc/product-delivery/v1/reserve")
@Produces({MediaType.APPLICATION_JSON, "application/x-ndjson"})
@Consumes({MediaType.APPLICATION_JSON, "application/x-ndjson"})
@RequiredArgsConstructor
public class ReservationRestService {

  private final ReservationService reservationService;
  private final ParamValidator paramValidator;

  @Context
  HttpHeaders httpHeaders;

  @POST
  public Uni<ReservationResponseDto> reserve(ReservationRequestDto request) {
    Map<String, String> headers = RestServerUtils.extractHeadersAsMap(httpHeaders.getRequestHeaders());
    return paramValidator.validateAndGet(headers, DefaultHeaders.class)
        .flatMap(defaultHeaders -> reservationService.reserve(request));
  }
}
