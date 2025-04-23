package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import com.demo.poc.commons.custom.states.State;
import lombok.Getter;

@Getter
public class NoSuchStateHandlerException extends GenericException {

  public NoSuchStateHandlerException(State state) {
    super(ErrorDictionary.NO_SUCH_STATE_HANDLER.getMessage() + ": " + state, ErrorDictionary.parse(NoSuchStateHandlerException.class));
  }
}
