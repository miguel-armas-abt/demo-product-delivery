package com.demo.poc.entrypoint.orders;

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
public class PendingOrderMockService implements MockService {

  @Override
  public void loadMocks(ClientAndServer mockServer) {

    mockServer
        .when(request()
            .withMethod("GET")
            .withPath("/poc/delivery-requests/v1/pending-orders")
            .withQueryStringParameter("customerId", "60777575"))
        .respond(request -> {

          long randomDelay = generateRandomDelay();
          Header traceIdHeader = generateTraceId();

          return response()
              .withStatusCode(HttpStatusCode.OK_200.code())
              .withHeader(contentType("application/x-ndjson"))
              .withHeader(traceIdHeader)
              .withBody(readJsonAsString("mocks/orders/PendingOrder.200.json"))
              .withDelay(TimeUnit.MILLISECONDS, randomDelay);
        });
  }
}
