package com.demo.poc.commons.custom.states.context;

import com.demo.poc.commons.custom.states.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Context {

    protected State currentState;
    protected State previousState;
}
