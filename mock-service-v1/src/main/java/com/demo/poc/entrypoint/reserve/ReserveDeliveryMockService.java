package com.demo.poc.entrypoint.reserve;

import java.util.concurrent.TimeUnit;

import com.demo.poc.commons.config.MockService;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.MatchType;
import org.mockserver.model.Header;
import org.mockserver.model.HttpStatusCode;

import org.mockserver.model.JsonBody;
import org.springframework.stereotype.Component;

import static com.demo.poc.commons.utils.DelayGenerator.generateRandomDelay;
import static com.demo.poc.commons.utils.HeadersGenerator.contentType;
import static com.demo.poc.commons.utils.HeadersGenerator.generateTraceId;
import static com.demo.poc.commons.utils.JsonReader.readJSON;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@Component
public class ReserveDeliveryMockService implements MockService {

  private static final String JSON_REQUEST_BODY_WITHOUT_CAPACITY = "{\"reserveDate\":{\"timeRange\":\"14:00 - 17:00\"}}";

  @Override
  public void loadMocks(ClientAndServer mockServer) {

    mockServer
        .when(request()
            .withMethod("POST")
            .withPath("/poc/delivery-coordination/v1/reserve")
            .withBody(JsonBody.json(JSON_REQUEST_BODY_WITHOUT_CAPACITY, MatchType.ONLY_MATCHING_FIELDS)))
        .respond(request -> {

          long randomDelay = generateRandomDelay();
          Header traceIdHeader = generateTraceId();

          return response()
              .withStatusCode(HttpStatusCode.OK_200.code())
              .withHeader(contentType("application/json"))
              .withHeader(traceIdHeader)
              .withBody(readJSON("mocks/reserve/Reserve.without-capacity.200.json"))
              .withDelay(TimeUnit.MILLISECONDS, randomDelay);
        });

    mockServer
        .when(request()
            .withMethod("POST")
            .withPath("/poc/delivery-coordination/v1/reserve"))
        .respond(request -> {

          long randomDelay = generateRandomDelay();
          Header traceIdHeader = generateTraceId();

          return response()
              .withStatusCode(HttpStatusCode.OK_200.code())
              .withHeader(contentType("application/json"))
              .withHeader(traceIdHeader)
              .withBody(readJSON("mocks/reserve/Reserve.200.json"))
              .withDelay(TimeUnit.MILLISECONDS, randomDelay);
        });
  }
}
