package com.demo.service.entrypoint.availability.service;

import com.demo.service.entrypoint.availability.dto.request.AvailabilityRequestDto;
import com.demo.service.entrypoint.availability.dto.response.AvailabilityResponseDto;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;

public interface AvailabilityService {

  Uni<AvailabilityResponseDto> getAvailableDates(@Valid AvailabilityRequestDto request);
}
