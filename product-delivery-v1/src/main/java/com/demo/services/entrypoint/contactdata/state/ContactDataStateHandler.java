package com.demo.services.entrypoint.contactdata.state;

import com.demo.services.commons.states.StateHandler;
import com.demo.services.commons.states.context.ProductDeliveryContext;
import com.demo.services.commons.states.State;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class ContactDataStateHandler implements StateHandler<ProductDeliveryContext> {

  @Override
  public Uni<ProductDeliveryContext> validateAndGet(ProductDeliveryContext context) {
    return Uni.createFrom().item(context);
  }

  @Override
  public Uni<ProductDeliveryContext> next(ProductDeliveryContext context) {
    return Uni.createFrom().item(context)
        .map(ctx -> {
          ctx.setPreviousState(State.CONTACT_DATA);
          ctx.setCurrentState(State.AVAILABLE_DATES);
          return ctx;
        });
  }

  @Override
  public boolean supports(State currentState) {
    return State.CONTACT_DATA.equals(currentState);
  }
}
