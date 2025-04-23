package com.demo.poc.entrypoint.availability.state;

import com.demo.poc.commons.custom.states.OnState;
import com.demo.poc.commons.custom.states.StateHandler;
import com.demo.poc.commons.custom.states.context.ProductDeliveryContext;
import com.demo.poc.commons.custom.states.enums.State;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@OnState(state = State.AVAILABLE_DATES)
@ApplicationScoped
@RequiredArgsConstructor
public class AvailabilityStateHandler implements StateHandler<ProductDeliveryContext> {

  @Override
  public Uni<ProductDeliveryContext> handle(ProductDeliveryContext context) {
    context.setPreviousState(State.AVAILABLE_DATES);
    context.setCurrentState(State.RESERVATION);
    return Uni.createFrom().item(context);
  }
}
