package com.demo.poc.commons.custom.states.context.orders;

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
public class PendingOrderContext implements Serializable {

  private String id;
  private String channelId;
  private String creationDate;
  private String purchaseCode;
}
