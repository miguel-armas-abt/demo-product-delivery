package com.demo.poc.commons.config.di;

import com.demo.poc.commons.core.logging.ErrorThreadContextInjector;
import com.demo.poc.commons.core.logging.ThreadContextInjector;
import com.demo.poc.commons.core.properties.ConfigurationBaseProperties;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class LoggingConfig {

  @Produces
  public ThreadContextInjector threadContextInjector(ConfigurationBaseProperties properties) {
    return new ThreadContextInjector(properties);
  }

  @Produces
  public ErrorThreadContextInjector errorThreadContextInjector(ThreadContextInjector threadContextInjector) {
    return new ErrorThreadContextInjector(threadContextInjector);
  }

}