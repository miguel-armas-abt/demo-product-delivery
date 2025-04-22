package com.demo.poc.commons.core.properties;

import com.demo.poc.commons.core.properties.restclient.RestClient;

import java.util.Map;

public interface ConfigurationBaseProperties {

  Map<String, String> errorMessages();

  Map<String, RestClient> restClients();

}