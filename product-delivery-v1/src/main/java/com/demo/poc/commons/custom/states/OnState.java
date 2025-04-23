package com.demo.poc.commons.custom.states;

import com.demo.poc.commons.custom.states.enums.State;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.METHOD;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, PARAMETER, METHOD})
public @interface OnState {
  State state();
}
