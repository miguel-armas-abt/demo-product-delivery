package com.demo.services.entrypoint.availability.service;

import com.demo.services.entrypoint.availability.dto.request.AvailabilityRequestDto;
import com.demo.services.entrypoint.availability.dto.response.AvailabilityResponseDto;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;

public interface AvailabilityService {

  Uni<AvailabilityResponseDto> getAvailableDates(@Valid AvailabilityRequestDto request);
}
