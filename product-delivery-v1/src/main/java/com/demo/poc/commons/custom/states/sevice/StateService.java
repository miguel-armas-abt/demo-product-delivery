package com.demo.poc.commons.custom.states.sevice;

import com.demo.poc.commons.custom.states.context.Context;
import com.demo.poc.commons.custom.states.enums.State;

public interface StateService {

  Context processState(Context context, State previousState);

}
