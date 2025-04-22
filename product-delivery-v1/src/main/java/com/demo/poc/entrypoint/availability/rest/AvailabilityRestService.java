package com.demo.poc.entrypoint.availability.rest;

import com.demo.poc.entrypoint.availability.dto.request.AvailabilityRequestDto;
import com.demo.poc.entrypoint.availability.dto.response.AvailabilityResponseDto;
import com.demo.poc.entrypoint.availability.service.AvailabilityService;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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

  @POST
  public Uni<AvailabilityResponseDto> getContactData(AvailabilityRequestDto request) {
    return availabilityService.getAvailableDates(request);
  }
}
