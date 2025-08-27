package com.demo.services.entrypoint.availability.dto.response;

import java.io.Serializable;
import java.util.List;

import com.demo.services.commons.states.dto.response.ContextResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailabilityResponseDto implements Serializable {

  private List<AvailableDateDto> availableDates;
  private ContextResponse context;
}
