package com.demo.poc.entrypoint.reservation.service;

import com.demo.poc.entrypoint.reservation.dto.request.ReservationRequestDto;
import com.demo.poc.entrypoint.reservation.dto.response.ReservationResponseDto;
import io.smallrye.mutiny.Uni;

public interface ReservationService {

  Uni<ReservationResponseDto> reserve(ReservationRequestDto request);
}
