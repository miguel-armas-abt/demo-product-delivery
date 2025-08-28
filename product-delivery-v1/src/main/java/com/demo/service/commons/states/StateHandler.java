package com.demo.service.commons.states;

import com.demo.service.commons.exceptions.InvalidCurrentStateException;
import com.demo.service.commons.states.context.Context;
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
