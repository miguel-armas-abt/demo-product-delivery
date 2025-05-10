package com.demo.poc.commons.custom.states.context.contactdata;

import java.io.Serializable;

import io.quarkus.runtime.annotations.RegisterForReflection;
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
@RegisterForReflection
public class AddressContext implements Serializable {

  private String priority;
  private String type;
  private String ubigeo;
  private StreetContext street;
  private BlockDto block;
  private String inside;
  private String references;

  @Builder
  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  @RegisterForReflection
  public static class StreetContext implements Serializable{

    private String name;
    private String type;
    private String number;
  }

  @Builder
  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  @RegisterForReflection
  public static class BlockDto implements Serializable{

    private String name;
    private String lot;
  }
}