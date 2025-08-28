package com.demo.service.entrypoint.reservation.repository.wrapper.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestWrapper implements Serializable {

  private String pendingOrderId;
  private ReserveDateWrapper reserveDate;

  @Builder
  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ReserveDateWrapper implements Serializable {
    private String date;
    private String timeRange;
  }
}
