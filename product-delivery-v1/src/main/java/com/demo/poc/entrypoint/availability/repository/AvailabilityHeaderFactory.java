package com.demo.poc.entrypoint.availability.repository;

import com.demo.poc.commons.core.properties.restclient.HeaderTemplate;
import com.demo.poc.commons.core.restclient.utils.HeadersFiller;
import com.demo.poc.commons.custom.properties.ApplicationProperties;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

@Provider
@RequiredArgsConstructor
public class AvailabilityHeaderFactory implements ClientHeadersFactory {

  private static final String SERVICE_NAME = "availability";
  private final ApplicationProperties properties;

  @Override
  public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders,
                                               MultivaluedMap<String, String> outgoingHeaders) {

    HeaderTemplate headerTemplate = properties.restClients().get(SERVICE_NAME).request().headers();
    return HeadersFiller.buildHeaders(headerTemplate, incomingHeaders);
  }
}