package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import com.demo.poc.commons.custom.states.enums.State;
import lombok.Getter;

@Getter
public class StateHandlerNotFoundException extends GenericException {

  public StateHandlerNotFoundException(State state) {
    super(ErrorDictionary.STATE_HANDLER_NOT_FOUND.getMessage() + ": " + state, ErrorDictionary.parse(StateHandlerNotFoundException.class));
  }
}
