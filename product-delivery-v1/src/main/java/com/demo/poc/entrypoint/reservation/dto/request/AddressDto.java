package com.demo.poc.entrypoint.reservation.dto.request;

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
public class AddressDto implements Serializable {

  @NotEmpty
  private String priority;

  private String ubigeo;
  private StreetDto street;
  private BlockDto block;
  private String inside;
  private String references;

  @Builder
  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class StreetDto implements Serializable{

    private String name;
    private String type;
    private String number;
  }

  @Builder
  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class BlockDto implements Serializable{

    private String name;
    private String lot;
  }
}