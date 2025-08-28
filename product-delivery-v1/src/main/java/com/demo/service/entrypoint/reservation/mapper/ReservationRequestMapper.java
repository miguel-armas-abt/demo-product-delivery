package com.demo.service.entrypoint.reservation.mapper;

import com.demo.commons.config.mapper.MappingConfig;
import com.demo.service.commons.states.context.ProductDeliveryContext;
import com.demo.service.commons.states.context.availability.AvailableDateContext;
import com.demo.service.commons.utils.TimeRangeSelectorUtil;
import com.demo.service.entrypoint.reservation.dto.request.ReservationRequestDto;
import com.demo.service.entrypoint.reservation.repository.wrapper.request.ReservationRequestWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;

@Mapper(config = MappingConfig.class)
public interface ReservationRequestMapper {

  @Mapping(target = "pendingOrderId", source = "context.pendingOrder.id")
  @Mapping(target = "reserveDate", expression = "java(mapReserveDate(context, request))")
  ReservationRequestWrapper toRequest(ProductDeliveryContext context, ReservationRequestDto request);

  default ReservationRequestWrapper.ReserveDateWrapper mapReserveDate(ProductDeliveryContext context,
                                                                      ReservationRequestDto request) {
    String timeRangeId = request.getTimeRangeId();
    List<AvailableDateContext> availableDates = context.getAvailableDates();
    Map.Entry<String,String> dateAndTime = TimeRangeSelectorUtil.searchDateAndTimeRange(timeRangeId, availableDates);

    return ReservationRequestWrapper.ReserveDateWrapper.builder()
        .date(dateAndTime.getKey())
        .timeRange(dateAndTime.getValue())
        .build();
  }

}
