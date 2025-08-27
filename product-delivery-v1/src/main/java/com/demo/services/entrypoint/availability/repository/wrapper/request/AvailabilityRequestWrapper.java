package com.demo.services.entrypoint.availability.repository.wrapper.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityRequestWrapper implements Serializable {

  private String customerId;
  private String pendingOrderId;
  private String ubigeo;
  private String courierCode;

}