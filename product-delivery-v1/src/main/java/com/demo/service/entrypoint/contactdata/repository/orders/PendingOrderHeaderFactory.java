package com.demo.service.entrypoint.contactdata.repository.orders;

import com.demo.commons.properties.restclient.HeaderTemplate;
import com.demo.commons.restclient.utils.HeadersFiller;
import com.demo.service.commons.properties.ApplicationProperties;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

@Provider
@RequiredArgsConstructor
public class PendingOrderHeaderFactory implements ClientHeadersFactory {

  private static final String SERVICE_NAME = "pending-orders";
  private final ApplicationProperties properties;

  @Override
  public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders,
                                               MultivaluedMap<String, String> outgoingHeaders) {

    HeaderTemplate headerTemplate = properties.restClients().get(SERVICE_NAME).request().headers();
    return HeadersFiller.fillHeaders(headerTemplate, incomingHeaders);
  }
}