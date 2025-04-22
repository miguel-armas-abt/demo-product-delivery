package com.demo.poc.entrypoint.contactdata.dto.response;

import java.io.Serializable;

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
public class AddressDto implements Serializable {

  private String priority;
  private String ubigeo;
  private StreetDto street;
  private BlockDto block;
  private String inside;

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
