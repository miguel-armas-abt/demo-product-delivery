package com.demo.poc.entrypoint.reservation;

import com.demo.poc.entrypoint.reservation.dto.request.ReservationRequestDto;
import com.demo.poc.entrypoint.reservation.dto.response.ReservationResponseDto;
import com.demo.poc.entrypoint.reservation.service.ReservationService;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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

  @POST
  public Uni<ReservationResponseDto> reserve(ReservationRequestDto request) {
    return reservationService.reserve(request);
  }
}
