package com.demo.poc.entrypoint.availability.service;

import com.demo.poc.commons.custom.exceptions.NotFoundAvailableDatesException;
import com.demo.poc.commons.custom.states.StateBuilder;
import com.demo.poc.commons.custom.states.context.Context;
import com.demo.poc.commons.custom.states.context.ProductDeliveryContext;
import com.demo.poc.commons.custom.states.enums.State;
import com.demo.poc.commons.custom.states.sevice.StateService;
import com.demo.poc.entrypoint.availability.dto.request.AvailabilityRequestDto;
import com.demo.poc.entrypoint.availability.dto.response.AvailabilityResponseDto;
import com.demo.poc.entrypoint.availability.mapper.AvailabilityContextMapper;
import com.demo.poc.entrypoint.availability.mapper.AvailabilityRequestMapper;
import com.demo.poc.entrypoint.availability.mapper.AvailabilityResponseMapper;
import com.demo.poc.entrypoint.availability.repository.AvailabilityRepository;
import com.demo.poc.entrypoint.availability.repository.wrapper.request.AvailabilityRequestWrapper;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@RequiredArgsConstructor
@ApplicationScoped
public class AvailabilityServiceImpl implements AvailabilityService, StateService {

  @RestClient
  private AvailabilityRepository repository;

  private final StateBuilder stateBuilder;
  private final AvailabilityRequestMapper requestMapper;
  private final AvailabilityContextMapper contextMapper;
  private final AvailabilityResponseMapper responseMapper;

  @Override
  public Uni<AvailabilityResponseDto> getAvailableDates(AvailabilityRequestDto request) {
    Context context = stateBuilder.of((request.getContext().getCiphered())).build();
    State previousState  = State.valueOf(request.getContext().getPreviousState());

    return Uni.createFrom()
        .item(this.processState(context, previousState))
        .flatMap(currentContext -> {
          AvailabilityRequestWrapper requestWrapper = requestMapper.toRequest((ProductDeliveryContext) currentContext, request, "49");
          return this.repository.getAvailableDates(requestWrapper)
              .onCompletion().ifEmpty().failWith(new NotFoundAvailableDatesException())
              .collect().asList()
              .map(contextMapper::toContext)
              .map(availabilityContext -> {
                ((ProductDeliveryContext) currentContext).setAvailableDates(availabilityContext);
                return stateBuilder.of(currentContext).buildAsResponse();
              })
              .map(contextResponse -> responseMapper.toResponse(contextResponse, ((ProductDeliveryContext) currentContext).getAvailableDates()));
        });
  }

  @Override
  public Context processState(Context context, State previousState) {
    return stateBuilder.of(context)
        .continueToNextState(() -> State.RESERVATION)
        .build();
  }
}
