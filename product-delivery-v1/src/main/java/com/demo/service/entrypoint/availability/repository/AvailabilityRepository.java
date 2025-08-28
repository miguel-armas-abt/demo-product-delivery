package com.demo.service.entrypoint.availability.repository;

import com.demo.commons.interceptor.restclient.RestClientRequestInterceptor;
import com.demo.commons.interceptor.restclient.RestClientResponseInterceptor;
import com.demo.service.entrypoint.availability.repository.wrapper.request.AvailabilityRequestWrapper;
import com.demo.service.entrypoint.availability.repository.wrapper.response.AvailabilityResponseWrapper;
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