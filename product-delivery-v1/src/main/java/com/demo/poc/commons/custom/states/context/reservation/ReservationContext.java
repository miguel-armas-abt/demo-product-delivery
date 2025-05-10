package com.demo.poc.commons.custom.states.context.reservation;

import io.quarkus.runtime.annotations.RegisterForReflection;
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
@RegisterForReflection
public class ReservationContext implements Serializable {

  private String reservationId;
  private boolean retry;
}