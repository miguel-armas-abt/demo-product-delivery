package com.demo.poc.commons.core.config;

import com.demo.poc.commons.core.logging.ThreadContextInjector;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class LoggingConfig {

  @Produces
  public ThreadContextInjector threadContextInjector() {
    return new ThreadContextInjector();
  }

}