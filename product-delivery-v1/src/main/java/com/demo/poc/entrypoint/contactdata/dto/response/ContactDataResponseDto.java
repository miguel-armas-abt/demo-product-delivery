package com.demo.poc.entrypoint.contactdata.dto.response;

import java.io.Serializable;
import java.util.List;

import com.demo.poc.commons.custom.states.dto.response.ContextResponse;
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
public class ContactDataResponseDto implements Serializable {

  private List<AddressDto> addresses;
  private ContextResponse context;

}