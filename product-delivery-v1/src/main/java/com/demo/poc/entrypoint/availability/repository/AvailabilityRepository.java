package com.demo.poc.entrypoint.availability.repository;

import com.demo.poc.commons.core.interceptor.restclient.RestClientRequestInterceptor;
import com.demo.poc.commons.core.interceptor.restclient.RestClientResponseInterceptor;
import com.demo.poc.entrypoint.availability.repository.wrapper.request.AvailabilityRequestWrapper;
import com.demo.poc.entrypoint.availability.repository.wrapper.response.AvailabilityResponseWrapper;
import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/available-dates")
@RegisterRestClient(configKey="availability")
@RegisterClientHeaders(AvailabilityHeaderFactory.class)
@RegisterProvider(RestClientRequestInterceptor.class)
@RegisterProvider(RestClientResponseInterceptor.class)
@Produces(MediaType.APPLICATION_JSON)
public interface AvailabilityRepository {

  @POST
  Multi<AvailabilityResponseWrapper> getAvailableDates(AvailabilityRequestWrapper request);
}