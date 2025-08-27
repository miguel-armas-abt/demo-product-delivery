package com.demo.services.entrypoint.availability.mapper;

import com.demo.commons.config.mapper.MappingConfig;
import com.demo.services.commons.states.context.ProductDeliveryContext;
import com.demo.services.entrypoint.availability.dto.request.AvailabilityRequestDto;
import com.demo.services.entrypoint.availability.repository.wrapper.request.AvailabilityRequestWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappingConfig.class)
public interface AvailabilityRequestMapper {

  @Mapping(target = "customerId", source = "request.customerId")
  @Mapping(target = "ubigeo", source = "request.ubigeo")
  @Mapping(target = "courierCode", source = "courierCode")
  @Mapping(target = "pendingOrderId", source = "context.pendingOrder.id")
  AvailabilityRequestWrapper toRequest(ProductDeliveryContext context, AvailabilityRequestDto request, String courierCode);
}
