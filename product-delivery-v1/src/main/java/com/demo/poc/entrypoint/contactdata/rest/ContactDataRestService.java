package com.demo.poc.entrypoint.contactdata.rest;

import com.demo.poc.entrypoint.contactdata.dto.request.ContactDataRequestDto;
import com.demo.poc.entrypoint.contactdata.dto.response.ContactDataResponseDto;
import com.demo.poc.entrypoint.contactdata.service.ContactDataService;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/poc/product-delivery/v1/contact-data")
@Produces({MediaType.APPLICATION_JSON, "application/x-ndjson"})
@Consumes({MediaType.APPLICATION_JSON, "application/x-ndjson"})
@RequiredArgsConstructor
public class ContactDataRestService {

  private final ContactDataService contactDataService;

  @POST
  public Uni<ContactDataResponseDto> getContactData(ContactDataRequestDto request) {
    return contactDataService.getContactData(request, "WEB");
  }
}