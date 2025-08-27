package com.demo.poc.commons.config.di;

import com.demo.poc.commons.core.properties.ConfigurationBaseProperties;
import com.demo.poc.commons.properties.ApplicationProperties;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class PropertiesDIConfig {

  @Produces
  public ConfigurationBaseProperties configurationBaseProperties(ApplicationProperties properties) {
    return properties;
  }
}
