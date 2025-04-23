package com.demo.poc.commons.custom.states;

import com.demo.poc.commons.custom.exceptions.OnStateAnnotationNotFoundException;
import com.demo.poc.commons.custom.exceptions.StateHandlerNotFoundException;
import com.demo.poc.commons.custom.states.context.ProductDeliveryContext;
import com.demo.poc.commons.custom.states.enums.State;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ApplicationScoped
public class StateDispatcher {

  private final Map<State, StateHandler<ProductDeliveryContext>> handlers;

  @Inject
  public StateDispatcher(@Any Instance<StateHandler<ProductDeliveryContext>> instances) {
    this.handlers = StreamSupport.stream(instances.spliterator(), false)
        .map(handler -> Optional.ofNullable(handler.getClass().getAnnotation(OnState.class))
            .map(onState -> Map.entry(onState.state(), handler))
            .orElseThrow(() -> new OnStateAnnotationNotFoundException(handler.getClass().getName())))
        .collect(Collectors.toUnmodifiableMap(
            Map.Entry::getKey,
            Map.Entry::getValue
        ));
  }

  public Uni<ProductDeliveryContext> dispatch(ProductDeliveryContext context) {
    return Optional.ofNullable(handlers.get(context.getCurrentState()))
        .map(handler -> handler.handle(context))
        .orElseGet(() -> Uni.createFrom().failure(() -> new StateHandlerNotFoundException(context.getCurrentState())));
  }
}
