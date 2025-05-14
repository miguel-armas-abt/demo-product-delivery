package com.demo.poc.commons.custom.config;

import com.demo.poc.commons.custom.states.context.Context;
import com.demo.poc.commons.custom.states.context.ProductDeliveryContext;
import com.demo.poc.commons.custom.states.context.availability.AvailableDateContext;
import com.demo.poc.commons.custom.states.context.contactdata.AddressContext;
import com.demo.poc.commons.custom.states.context.contactdata.ContactDataContext;
import com.demo.poc.commons.custom.states.context.orders.PendingOrderContext;
import com.demo.poc.commons.custom.states.context.reservation.ReservationContext;
import io.quarkus.runtime.annotations.RegisterForReflection;

//POJOs not referenced by JPA entities or JAX-RS endpoints
@RegisterForReflection(targets = {
    Context.class,
    ProductDeliveryContext.class,
    AvailableDateContext.class,
    ContactDataContext.class,
    PendingOrderContext.class,
    AvailableDateContext.TimeRangeContext.class,
    AddressContext.class,
    AddressContext.StreetContext.class,
    AddressContext.BlockContext.class,
    ReservationContext.class,
})
public class ReflectionConfig {
}
