package com.demo.poc.commons.custom.states;

import java.util.Objects;
import java.util.function.Supplier;

import com.demo.poc.commons.custom.exceptions.InvalidCurrentStateException;
import com.demo.poc.commons.custom.exceptions.InvalidNextStateException;
import com.demo.poc.commons.custom.exceptions.InvalidPreviousStateException;
import com.demo.poc.commons.custom.states.context.Context;
import com.demo.poc.commons.custom.states.dto.response.ContextResponse;
import com.demo.poc.commons.custom.states.enums.State;
import com.demo.poc.commons.custom.states.mapper.ContextMapper;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ApplicationScoped
public class StateBuilder {

  private Context currentContext;

  private final ContextMapper mapper;

  public StateBuilder init(Context context) {
    this.currentContext = context;
    this.currentContext.setCurrentState(State.firstSate());
    return this;
  }

  public StateBuilder of(Context currentContext) {
    this.currentContext = currentContext;
    validateCurrentState(currentContext);
    validatePreviousState(this.currentContext, currentContext.getPreviousState());
    this.currentContext = currentContext;
    return this;
  }

  public StateBuilder of(String ciphered) {
    Context recoveredContext = mapper.toContext(ciphered);
    return of(recoveredContext);
  }

  public StateBuilder continueToNextState(Supplier<State> nextStateSupplier) {
    State currentState = this.currentContext.getCurrentState();
    State nextState = nextStateSupplier.get();
    validateNextState(this.currentContext, nextState);
    this.currentContext.setPreviousState(currentState);
    this.currentContext.setCurrentState(nextState);
    return this;
  }

  private void validateCurrentState(Context currentContext) {
    if (Objects.isNull(currentContext) || Objects.isNull(currentContext.getCurrentState())) {
      throw new InvalidCurrentStateException();
    }
  }

  private void validatePreviousState(Context currentContext, State validablePreviousState) {
    State currentState = currentContext.getCurrentState();
    if(!currentState.equals(State.firstSate()) && !validablePreviousState.equals(currentContext.getPreviousState()))
      throw new InvalidPreviousStateException(validablePreviousState.name());
  }

  private void validateNextState(Context currentContext, State validableNextState) {
    boolean isValidNextState = currentContext.getCurrentState().nextStates().contains(validableNextState);
    if (!isValidNextState) {
      throw new InvalidNextStateException(validableNextState.name());
    }
  }

  public Context build() {
    return this.currentContext;
  }

  public ContextResponse buildAsResponse() {
    return mapper.toResponse(this.currentContext);
  }
}
