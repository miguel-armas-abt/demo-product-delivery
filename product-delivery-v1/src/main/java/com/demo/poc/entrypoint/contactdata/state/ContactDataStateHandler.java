package com.demo.poc.entrypoint.contactdata.state;

import com.demo.poc.commons.custom.states.OnState;
import com.demo.poc.commons.custom.states.StateHandler;
import com.demo.poc.commons.custom.states.context.ProductDeliveryContext;
import com.demo.poc.commons.custom.states.enums.State;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@OnState(state = State.CONTACT_DATA)
@ApplicationScoped
@RequiredArgsConstructor
public class ContactDataStateHandler implements StateHandler<ProductDeliveryContext> {

  @Override
  public Uni<ProductDeliveryContext> handle(ProductDeliveryContext context) {
    context.setPreviousState(State.CONTACT_DATA);
    context.setCurrentState(State.AVAILABLE_DATES);
    return Uni.createFrom().item(context);
  }
}
