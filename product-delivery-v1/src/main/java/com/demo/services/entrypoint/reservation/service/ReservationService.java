package com.demo.services.entrypoint.reservation.service;

import com.demo.services.entrypoint.reservation.dto.request.ReservationRequestDto;
import com.demo.services.entrypoint.reservation.dto.response.ReservationResponseDto;
import io.smallrye.mutiny.Uni;

public interface ReservationService {

  Uni<ReservationResponseDto> reserve(ReservationRequestDto request);
}
