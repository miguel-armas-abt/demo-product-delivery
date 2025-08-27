package com.demo.poc.entrypoint.reservation.dto.request;

import com.demo.poc.commons.states.dto.request.ContextRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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
public class ReservationRequestDto implements Serializable {
  @NotEmpty
  private String customerId;

  @NotEmpty
  private String timeRangeId;

  @Valid
  private AddressDto address;

  @Valid
  private ContextRequest context;
}
