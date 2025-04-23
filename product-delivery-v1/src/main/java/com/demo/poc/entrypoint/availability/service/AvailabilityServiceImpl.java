package com.demo.poc.entrypoint.availability.service;

import com.demo.poc.commons.custom.exceptions.NotFoundAvailableDatesException;
import com.demo.poc.commons.custom.states.StateDispatcher;
import com.demo.poc.commons.custom.states.context.ProductDeliveryContext;
import com.demo.poc.commons.custom.states.context.availability.AvailableDateContext;
import com.demo.poc.commons.custom.states.dto.response.ContextResponse;
import com.demo.poc.commons.custom.states.mapper.ContextMapper;
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

import java.util.List;

@RequiredArgsConstructor
@ApplicationScoped
public class AvailabilityServiceImpl implements AvailabilityService {

  @RestClient
  private AvailabilityRepository repository;

  private final StateDispatcher stateDispatcher;
  private final AvailabilityRequestMapper requestMapper;
  private final AvailabilityContextMapper availabilityContextMapper;
  private final AvailabilityResponseMapper responseMapper;
  private final ContextMapper contextMapper;

  @Override
  public Uni<AvailabilityResponseDto> getAvailableDates(AvailabilityRequestDto request) {
    ProductDeliveryContext context = contextMapper.toContext(request.getContext().getCiphered());
    AvailabilityRequestWrapper requestWrapper = requestMapper.toRequest(context, request, "49");

    return this.repository.getAvailableDates(requestWrapper)
        .onCompletion().ifEmpty().failWith(new NotFoundAvailableDatesException())
        .collect().asList()
        .map(availableDatesResponse -> {
          List<AvailableDateContext> availableDatesContext =  availabilityContextMapper.toContext(availableDatesResponse);
          context.setAvailableDates(availableDatesContext);
          return context;
        })
        .flatMap(stateDispatcher::dispatch)
        .map(ctx -> {
          ContextResponse contextResponse = contextMapper.toResponse(ctx);
          return responseMapper.toResponse(contextResponse, ctx.getAvailableDates());
        });
  }
}
