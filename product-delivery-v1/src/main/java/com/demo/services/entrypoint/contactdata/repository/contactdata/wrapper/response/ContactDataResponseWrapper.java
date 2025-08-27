package com.demo.services.entrypoint.contactdata.repository.contactdata.wrapper.response;

import java.io.Serializable;
import java.util.List;

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
public class ContactDataResponseWrapper implements Serializable {

  private String customerId;
  private String documentType;
  private String documentNumber;
  private String names;
  private String surnames;
  private List<EmailWrapper> emails;
  private List<PhoneWrapper> phones;
  private List<AddressWrapper> addresses;

  @Builder
  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class EmailWrapper implements Serializable {

    private String priority;
    private String type;
    private String value;
  }

  @Builder
  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class PhoneWrapper implements Serializable {

    private String priority;
    private String type;
    private String number;
  }
}