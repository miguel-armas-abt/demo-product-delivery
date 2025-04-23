package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class OnStateAnnotationNotFoundException extends GenericException {

  public OnStateAnnotationNotFoundException(String className) {
    super(ErrorDictionary.ON_STATE_ANNOTATION_NOT_FOUND.getMessage() + ": " + className, ErrorDictionary.parse(OnStateAnnotationNotFoundException.class));
  }
}
