package com.demo.services.entrypoint.availability.dto.request;

import java.io.Serializable;

import com.demo.services.commons.states.dto.request.ContextRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class AvailabilityRequestDto implements Serializable {

  @NotEmpty
  @Pattern(regexp = "\\d{6}")
  private String ubigeo;

  @NotEmpty
  private String customerId;

  @NotNull
  @Pattern(regexp = "^(true|false)$")
  private String authorizeThird;

  @Valid
  private ContextRequest context;
}
