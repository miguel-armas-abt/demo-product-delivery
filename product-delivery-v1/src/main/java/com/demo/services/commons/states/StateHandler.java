package com.demo.services.commons.states;

import com.demo.services.commons.exceptions.InvalidCurrentStateException;
import com.demo.services.commons.states.context.Context;
import io.smallrye.mutiny.Uni;

public interface StateHandler<C extends Context> {

  default Uni<C> validateCurrentState(C context) {
    State currentState = context.getCurrentState();
    if(!supports(currentState)) {
      return Uni.createFrom().failure((new InvalidCurrentStateException(currentState)));
    }
    return Uni.createFrom().item(context);
  }

  Uni<C> validateAndGet(C context);

  Uni<C> next(C context);

  boolean supports(State currentState);
}
