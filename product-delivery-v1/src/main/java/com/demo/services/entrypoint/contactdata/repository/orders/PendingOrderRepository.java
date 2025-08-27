package com.demo.services.entrypoint.contactdata.repository.orders;

import com.demo.commons.interceptor.restclient.RestClientRequestInterceptor;
import com.demo.commons.interceptor.restclient.RestClientResponseInterceptor;
import com.demo.services.entrypoint.contactdata.repository.orders.wrapper.response.PendingOrderResponseWrapper;
import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/pending-orders")
@RegisterRestClient(configKey="pending-orders")
@RegisterClientHeaders(PendingOrderHeaderFactory.class)
@RegisterProvider(RestClientRequestInterceptor.class)
@RegisterProvider(RestClientResponseInterceptor.class)
@Produces({"application/x-ndjson"})
public interface PendingOrderRepository {

  @GET
  Multi<PendingOrderResponseWrapper> getPendingOrders(@QueryParam("customerId") String customerId);
}