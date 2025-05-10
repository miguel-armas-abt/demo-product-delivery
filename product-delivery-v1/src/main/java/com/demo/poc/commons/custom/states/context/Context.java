package com.demo.poc.commons.custom.states.context;

import com.demo.poc.commons.custom.states.State;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RegisterForReflection
public abstract class Context {

    protected State currentState;
    protected State previousState;
}
