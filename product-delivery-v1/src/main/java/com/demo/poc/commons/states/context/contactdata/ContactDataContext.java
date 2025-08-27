package com.demo.poc.commons.states.context.contactdata;

import java.io.Serializable;
import java.util.List;

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
public class ContactDataContext implements Serializable {

  private String customerId;
  private String documentType;
  private String documentNumber;
  private String names;
  private String surnames;
  private String email;
  private String phone;
  private List<AddressContext> addresses;
}
