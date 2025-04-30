package com.demo.poc.entrypoint.contactdata;

import java.util.concurrent.TimeUnit;

import com.demo.poc.commons.custom.config.MockService;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.HttpStatusCode;

import org.springframework.stereotype.Component;

import static com.demo.poc.commons.custom.utils.DelayUtil.generateRandomDelay;
import static com.demo.poc.commons.custom.utils.HeadersGenerator.contentType;
import static com.demo.poc.commons.custom.utils.HeadersGenerator.generateTraceId;
import static com.demo.poc.commons.custom.utils.JsonReader.readJsonAsString;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@Component
public class ContactDataMockService implements MockService {

  @Override
  public void loadMocks(ClientAndServer mockServer) {

    mockServer
        .when(request()
            .withMethod("GET")
            .withPath("/poc/customer-search/v1/contact-data/60777575"))
        .respond(request -> {

          long randomDelay = generateRandomDelay();
          Header traceIdHeader = generateTraceId();

          return response()
              .withStatusCode(HttpStatusCode.OK_200.code())
              .withHeader(contentType("application/json"))
              .withHeader(traceIdHeader)
              .withBody(readJsonAsString("mocks/contactdata/ContactData.200.json"))
              .withDelay(TimeUnit.MILLISECONDS, randomDelay);
        });
  }
}
