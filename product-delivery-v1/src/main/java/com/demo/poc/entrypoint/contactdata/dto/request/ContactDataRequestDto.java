package com.demo.poc.entrypoint.contactdata.dto.request;

import java.io.Serializable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class ContactDataRequestDto implements Serializable {

  @NotEmpty
  private String pendingOrderId;

  @NotNull
  @Valid
  private Customer customer;

  @Builder
  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Customer {

    @NotEmpty
    private String customerId;

    @Email
    private String deliveryEmail;

    private String deliveryPhone;
  }

}
