package com.demo.poc.entrypoint.reservation.state;

import java.util.Objects;

import com.demo.poc.commons.custom.exceptions.NullAvailableDatesException;
import com.demo.poc.commons.custom.exceptions.NullContactDataContextException;
import com.demo.poc.commons.custom.states.StateHandler;
import com.demo.poc.commons.custom.states.context.ProductDeliveryContext;
import com.demo.poc.commons.custom.states.State;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class ReservationStateHandler implements StateHandler<ProductDeliveryContext> {

  @Override
  public Uni<ProductDeliveryContext> validateAndGet(ProductDeliveryContext context) {
    return validateCurrentState(context)
        .flatMap(ctx -> {
          if(Objects.isNull(ctx.getContactData()))
            return Uni.createFrom().failure(new NullContactDataContextException());

          if (Objects.isNull(ctx.getAvailableDates()))
            return Uni.createFrom().failure(new NullAvailableDatesException());

          return Uni.createFrom().item(ctx);
        });
  }

  @Override
  public Uni<ProductDeliveryContext> next(ProductDeliveryContext context) {
    return Uni.createFrom().item(context)
        .map(ctx -> {
          ctx.setPreviousState(State.RESERVATION);

          State nextState = ctx.getReservation().isRetry() ? State.RETRY : State.COORDINATION;
          ctx.setCurrentState(nextState);

          return ctx;
        });
  }

  @Override
  public boolean supports(State currentState) {
    return State.RESERVATION.equals(currentState);
  }
}
