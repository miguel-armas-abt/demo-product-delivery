package com.demo.poc.commons.custom.states.context;

import java.io.Serializable;

import com.demo.poc.commons.custom.states.context.contactdata.ContactDataContext;
import com.demo.poc.commons.custom.states.context.orders.PendingOrderContext;
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
public class ProductDeliveryContext extends Context implements Serializable {

  private PendingOrderContext pendingOrder;
  private ContactDataContext contactData;
}
