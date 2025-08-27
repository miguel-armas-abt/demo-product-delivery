package com.demo.poc.commons.states;

import com.demo.poc.commons.exceptions.NoSuchStateHandlerException;
import com.demo.poc.commons.states.context.ProductDeliveryContext;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class StateDispatcher {

  private final Instance<StateHandler<ProductDeliveryContext>> handlers;

  @Inject
  public StateDispatcher(Instance<StateHandler<ProductDeliveryContext>> handlers) {
    this.handlers = handlers;
  }

  public Uni<ProductDeliveryContext> next(ProductDeliveryContext context) {
    return selectStateHandler(context.getCurrentState()).next(context);
  }

  public Uni<ProductDeliveryContext> validateAndGet(ProductDeliveryContext context, State state) {
    return selectStateHandler(state).validateAndGet(context);
  }

  private StateHandler<ProductDeliveryContext> selectStateHandler(State state) {
    return handlers.stream()
        .filter(stateHandler -> stateHandler.supports(state))
        .findFirst()
        .orElseThrow(() -> new NoSuchStateHandlerException(state));
  }
}
