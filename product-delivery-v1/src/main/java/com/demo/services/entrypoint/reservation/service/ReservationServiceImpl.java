package com.demo.services.entrypoint.reservation.service;

import com.demo.services.commons.states.StateDispatcher;
import com.demo.services.commons.states.context.ProductDeliveryContext;
import com.demo.services.commons.states.context.availability.AvailableDateContext;
import com.demo.services.commons.states.context.reservation.ReservationContext;
import com.demo.services.commons.states.dto.response.ContextResponse;
import com.demo.services.commons.states.State;
import com.demo.services.commons.states.mapper.ContextMapper;
import com.demo.services.entrypoint.reservation.dto.request.ReservationRequestDto;
import com.demo.services.entrypoint.reservation.dto.response.ReservationResponseDto;
import com.demo.services.entrypoint.reservation.mapper.ReservationContextMapper;
import com.demo.services.entrypoint.reservation.mapper.ReservationRequestMapper;
import com.demo.services.entrypoint.reservation.mapper.ReservationResponseMapper;
import com.demo.services.entrypoint.reservation.repository.ReservationRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

  @RestClient
  private ReservationRepository repository;

  private final StateDispatcher stateDispatcher;
  private final ReservationRequestMapper requestMapper;
  private final ReservationContextMapper reservationContextMapper;
  private final ContextMapper contextMapper;
  private final ReservationResponseMapper responseMapper;

  @Override
  public Uni<ReservationResponseDto> reserve(ReservationRequestDto request) {
    ProductDeliveryContext context = contextMapper.toContext(request.getContext().getCiphered());

    return stateDispatcher.validateAndGet(context, State.RESERVATION)
        .map(ctx -> requestMapper.toRequest(ctx, request))
        .flatMap(requestWrapper -> repository.reserve(requestWrapper))
        .flatMap(reservationResponse -> {
          ReservationContext reservationContext = reservationContextMapper.toContext(reservationResponse);
          context.setReservation(reservationContext);

          if(reservationResponse.isRetry()) {
            List<AvailableDateContext> availableDates = reservationContextMapper.toAvailableDates(reservationResponse.getAvailableDates());
            context.setAvailableDates(availableDates);
          }

          return stateDispatcher.next(context);
        })
        .map(ctx -> {
          ContextResponse contextResponse = contextMapper.toResponse(ctx);

          return ctx.getReservation().isRetry()
              ? responseMapper.toResponse(contextResponse, ctx.getAvailableDates())
              : responseMapper.toResponse(contextResponse);
        });
  }
}