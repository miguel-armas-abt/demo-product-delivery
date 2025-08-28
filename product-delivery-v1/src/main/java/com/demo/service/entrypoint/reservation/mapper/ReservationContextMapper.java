package com.demo.service.entrypoint.reservation.mapper;

import com.demo.commons.config.mapper.MappingConfig;
import com.demo.service.commons.states.context.availability.AvailableDateContext;
import com.demo.service.commons.states.context.reservation.ReservationContext;
import com.demo.service.commons.utils.TimeRangeUtils;
import com.demo.service.entrypoint.reservation.repository.wrapper.response.ReservationResponseWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = MappingConfig.class)
public interface ReservationContextMapper {

  ReservationContext toContext(ReservationResponseWrapper reservationResponse);

  @Mapping(target = "availableDates", source = "availableDates")
  List<AvailableDateContext> toAvailableDates(List<ReservationResponseWrapper.AvailableDate> availableDates);

  @Mapping(target = "timeRanges", source = "ranges", qualifiedByName = "mapTimeRanges")
  AvailableDateContext mapAvailableDate(ReservationResponseWrapper.AvailableDate availableDate);

  @Named("mapTimeRanges")
  static List<AvailableDateContext.TimeRangeContext> mapTimeRanges(List<String> timeRanges) {
    return timeRanges.stream()
        .map(TimeRangeUtils::createTimeRange)
        .toList();
  }
}
