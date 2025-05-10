package com.demo.poc.commons.custom.states.context;

import java.io.Serializable;
import java.util.List;

import com.demo.poc.commons.custom.states.context.availability.AvailableDateContext;
import com.demo.poc.commons.custom.states.context.contactdata.ContactDataContext;
import com.demo.poc.commons.custom.states.context.orders.PendingOrderContext;
import com.demo.poc.commons.custom.states.context.reservation.ReservationContext;
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
public class ProductDeliveryContext extends Context implements Serializable {

  private PendingOrderContext pendingOrder;
  private ContactDataContext contactData;
  private List<AvailableDateContext> availableDates;
  private ReservationContext reservation;
}
