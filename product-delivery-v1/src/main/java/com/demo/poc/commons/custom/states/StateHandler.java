package com.demo.poc.commons.custom.states;

import com.demo.poc.commons.custom.states.context.Context;
import io.smallrye.mutiny.Uni;

public interface StateHandler<C extends Context> {

  Uni<C> handle(C context);

}
