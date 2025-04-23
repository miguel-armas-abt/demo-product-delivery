package com.demo.poc.entrypoint.reservation.state;

import com.demo.poc.commons.custom.states.OnState;
import com.demo.poc.commons.custom.states.StateHandler;
import com.demo.poc.commons.custom.states.context.ProductDeliveryContext;
import com.demo.poc.commons.custom.states.enums.State;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@OnState(state = State.RESERVATION)
@ApplicationScoped
@RequiredArgsConstructor
public class ReservationStateHandler implements StateHandler<ProductDeliveryContext> {

  @Override
  public Uni<ProductDeliveryContext> handle(ProductDeliveryContext context) {
    context.setPreviousState(State.RESERVATION);

    State nextState = context.getReservation().isRetry() ? State.RETRY : State.COORDINATION;
    context.setCurrentState(nextState);

    return Uni.createFrom().item(context);
  }
}
