package com.demo.poc.commons.core.config;

import com.demo.poc.commons.core.interceptor.error.ConstraintErrorInterceptor;
import com.demo.poc.commons.core.interceptor.error.ErrorInterceptor;
import com.demo.poc.commons.core.logging.ErrorThreadContextInjector;
import com.demo.poc.commons.custom.properties.ApplicationProperties;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class InterceptorConfig {

  @Produces
  public ErrorInterceptor errorInterceptor(ApplicationProperties properties,
                                           ErrorThreadContextInjector contextInjector) {
    return new ErrorInterceptor(properties, contextInjector);
  }

  @Produces
  public ConstraintErrorInterceptor constraintErrorInterceptor(ErrorThreadContextInjector contextInjector) {
    return new ConstraintErrorInterceptor(contextInjector);
  }
}