package com.demo.service.commons.config.reflection;

import com.demo.service.commons.states.context.Context;
import com.demo.service.commons.states.context.ProductDeliveryContext;
import com.demo.service.commons.states.context.availability.AvailableDateContext;
import com.demo.service.commons.states.context.contactdata.AddressContext;
import com.demo.service.commons.states.context.contactdata.ContactDataContext;
import com.demo.service.commons.states.context.orders.PendingOrderContext;
import com.demo.service.commons.states.context.reservation.ReservationContext;
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
