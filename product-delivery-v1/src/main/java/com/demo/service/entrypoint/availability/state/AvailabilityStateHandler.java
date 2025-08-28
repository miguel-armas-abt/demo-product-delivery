package com.demo.service.entrypoint.availability.state;

import java.util.Objects;

import com.demo.service.commons.exceptions.NullContactDataContextException;
import com.demo.service.commons.states.StateHandler;
import com.demo.service.commons.states.context.ProductDeliveryContext;
import com.demo.service.commons.states.State;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class AvailabilityStateHandler implements StateHandler<ProductDeliveryContext> {

  @Override
  public Uni<ProductDeliveryContext> validateAndGet(ProductDeliveryContext context) {
    return validateCurrentState(context)
        .flatMap(ctx -> {
          if(Objects.isNull(ctx.getContactData()))
            return Uni.createFrom().failure(new NullContactDataContextException());
          return Uni.createFrom().item(ctx);
        });
  }

  @Override
  public Uni<ProductDeliveryContext> next(ProductDeliveryContext context) {
    return Uni.createFrom().item(context)
        .map(ctx -> {
          ctx.setPreviousState(State.AVAILABLE_DATES);
          ctx.setCurrentState(State.RESERVATION);
          return ctx;
        });
  }

  @Override
  public boolean supports(State currentState) {
    return State.AVAILABLE_DATES.equals(currentState);
  }
}
