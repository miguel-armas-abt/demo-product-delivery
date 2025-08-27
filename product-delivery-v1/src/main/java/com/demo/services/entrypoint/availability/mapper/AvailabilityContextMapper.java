package com.demo.services.entrypoint.availability.mapper;

import java.util.List;

import com.demo.commons.config.mapper.MappingConfig;
import com.demo.services.commons.states.context.availability.AvailableDateContext;
import com.demo.services.commons.utils.TimeRangeUtils;
import com.demo.services.entrypoint.availability.repository.wrapper.response.AvailabilityResponseWrapper;
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
