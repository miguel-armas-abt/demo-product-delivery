package com.demo.poc.commons.config.di;

import com.demo.poc.commons.core.errors.selector.ResponseErrorSelector;
import com.demo.poc.commons.core.properties.ConfigurationBaseProperties;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ErrorConfig {

  @Produces
  public ResponseErrorSelector responseErrorSelector(ConfigurationBaseProperties properties) {
    return new ResponseErrorSelector(properties);
  }

}
