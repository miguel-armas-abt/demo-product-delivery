package com.demo.service.entrypoint.contactdata.service;

import com.demo.service.entrypoint.contactdata.dto.request.ContactDataRequestDto;
import com.demo.service.entrypoint.contactdata.dto.response.ContactDataResponseDto;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;

public interface ContactDataService {

  Uni<ContactDataResponseDto> getContactData(@Valid ContactDataRequestDto request);

}
