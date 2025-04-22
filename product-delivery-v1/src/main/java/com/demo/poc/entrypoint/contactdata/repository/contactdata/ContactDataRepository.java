package com.demo.poc.entrypoint.contactdata.repository.contactdata;

import com.demo.poc.commons.core.interceptor.restclient.RestClientRequestInterceptor;
import com.demo.poc.commons.core.interceptor.restclient.RestClientResponseInterceptor;
import com.demo.poc.entrypoint.contactdata.repository.contactdata.wrapper.response.ContactDataResponseWrapper;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/contact-data")
@RegisterRestClient(configKey="contact-data")
@RegisterClientHeaders(ContactDataHeaderFactory.class)
@RegisterProvider(RestClientRequestInterceptor.class)
@RegisterProvider(RestClientResponseInterceptor.class)
@Produces(MediaType.APPLICATION_JSON)
public interface ContactDataRepository {

  @GET
  @Path("/{customerId}")
  Uni<ContactDataResponseWrapper> getContactData(@PathParam("customerId") String customerId);
}