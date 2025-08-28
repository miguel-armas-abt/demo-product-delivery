package com.demo.service.commons.states.context;

import com.demo.service.commons.states.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Context {

    protected State currentState;
    protected State previousState;
}
