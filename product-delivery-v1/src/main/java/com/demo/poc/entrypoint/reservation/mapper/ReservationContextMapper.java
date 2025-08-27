package com.demo.poc.entrypoint.reservation.mapper;

import com.demo.poc.commons.config.di.MappingConfig;
import com.demo.poc.commons.states.context.availability.AvailableDateContext;
import com.demo.poc.commons.states.context.reservation.ReservationContext;
import com.demo.poc.commons.utils.TimeRangeUtils;
import com.demo.poc.entrypoint.reservation.repository.wrapper.response.ReservationResponseWrapper;
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
