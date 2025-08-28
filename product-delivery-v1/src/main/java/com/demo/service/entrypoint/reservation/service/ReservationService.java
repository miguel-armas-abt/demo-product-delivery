package com.demo.service.entrypoint.reservation.service;

import com.demo.service.entrypoint.reservation.dto.request.ReservationRequestDto;
import com.demo.service.entrypoint.reservation.dto.response.ReservationResponseDto;
import io.smallrye.mutiny.Uni;

public interface ReservationService {

  Uni<ReservationResponseDto> reserve(ReservationRequestDto request);
}
