package com.demo.services.commons.states.context;

import java.io.Serializable;
import java.util.List;

import com.demo.services.commons.states.context.availability.AvailableDateContext;
import com.demo.services.commons.states.context.contactdata.ContactDataContext;
import com.demo.services.commons.states.context.orders.PendingOrderContext;
import com.demo.services.commons.states.context.reservation.ReservationContext;
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
  private List<AvailableDateContext> availableDates;
  private ReservationContext reservation;
}
