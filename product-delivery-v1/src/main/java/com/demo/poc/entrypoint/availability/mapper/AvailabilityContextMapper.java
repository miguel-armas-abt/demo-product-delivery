package com.demo.poc.entrypoint.availability.mapper;

import java.util.List;

import com.demo.poc.commons.config.di.MappingConfig;
import com.demo.poc.commons.states.context.availability.AvailableDateContext;
import com.demo.poc.commons.utils.TimeRangeUtils;
import com.demo.poc.entrypoint.availability.repository.wrapper.response.AvailabilityResponseWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MappingConfig.class)
public interface AvailabilityContextMapper {

  @Mapping(target = "availableDates", source = "availableDates")
  List<AvailableDateContext> toContext(List<AvailabilityResponseWrapper> availableDates);

  @Mapping(target = "timeRanges", source = "ranges", qualifiedByName = "mapTimeRanges")
  AvailableDateContext mapAvailableDate(AvailabilityResponseWrapper availableDate);

  @Named("mapTimeRanges")
  static List<AvailableDateContext.TimeRangeContext> mapTimeRanges(List<String> timeRanges) {
    return timeRanges.stream()
        .map(TimeRangeUtils::createTimeRange)
        .toList();
  }
}
