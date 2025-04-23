package com.demo.poc.entrypoint.reservation.repository;

import com.demo.poc.commons.core.interceptor.restclient.RestClientRequestInterceptor;
import com.demo.poc.commons.core.interceptor.restclient.RestClientResponseInterceptor;
import com.demo.poc.entrypoint.reservation.repository.wrapper.request.ReservationRequestWrapper;
import com.demo.poc.entrypoint.reservation.repository.wrapper.response.ReservationResponseWrapper;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/reserve")
@RegisterRestClient(configKey="reservation")
@RegisterClientHeaders(ReservationHeaderFactory.class)
@RegisterProvider(RestClientRequestInterceptor.class)
@RegisterProvider(RestClientResponseInterceptor.class)
@Produces({"application/json"})
public interface ReservationRepository {

  @POST
  Uni<ReservationResponseWrapper> reserve(ReservationRequestWrapper request);
}