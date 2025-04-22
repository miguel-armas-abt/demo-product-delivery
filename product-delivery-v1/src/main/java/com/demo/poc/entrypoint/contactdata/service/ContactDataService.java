package com.demo.poc.entrypoint.contactdata.service;

import com.demo.poc.entrypoint.contactdata.dto.request.ContactDataRequestDto;
import com.demo.poc.entrypoint.contactdata.dto.response.ContactDataResponseDto;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;

public interface ContactDataService {

  Uni<ContactDataResponseDto> getContactData(@Valid ContactDataRequestDto request, String channelId);

}
