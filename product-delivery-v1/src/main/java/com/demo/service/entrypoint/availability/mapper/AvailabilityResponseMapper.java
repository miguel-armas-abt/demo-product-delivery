package com.demo.service.entrypoint.availability.mapper;

import java.util.Comparator;
import java.util.List;

import com.demo.commons.config.mapper.MappingConfig;
import com.demo.service.commons.states.context.availability.AvailableDateContext;
import com.demo.service.commons.states.dto.response.ContextResponse;
import com.demo.service.commons.utils.DateUtils;
import com.demo.service.commons.utils.TimeRangeUtils;
import com.demo.service.entrypoint.availability.dto.response.AvailabilityResponseDto;
import com.demo.service.entrypoint.availability.dto.response.AvailableDateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MappingConfig.class)
public interface AvailabilityResponseMapper {

  @Mapping(target = "availableDates", source = "availableDates", qualifiedByName = "mapAvailableDates")
  AvailabilityResponseDto toResponse(ContextResponse context, List<AvailableDateContext> availableDates);

  @Named("mapAvailableDates")
  static List<AvailableDateDto> mapAvailableDates(List<AvailableDateContext> availableDates) {
    return availableDates.stream()
        .sorted(Comparator.comparing(AvailableDateContext::getDate))
        .map(AvailabilityResponseMapper::mapAvailableDate)
        .toList();
  }

  static AvailableDateDto mapAvailableDate(AvailableDateContext availableDate) {
    return AvailableDateDto.builder()
        .timeRanges(getTimeRanges(availableDate.getTimeRanges()))
        .date(getDate(availableDate.getDate()))
        .build();
  }

  static AvailableDateDto.DateDto getDate(String date) {
    String[] dateItems = DateUtils.mapDate(date).split(DateUtils.DATE_SEPARATOR);
    return AvailableDateDto.DateDto.builder()
        .day(AvailableDateDto.DayDto.builder()
            .abbreviation(dateItems[0])
            .number(dateItems[1])
            .build())
        .month(dateItems[2])
        .year(dateItems[3])
        .build();
  }

  static List<AvailableDateDto.TimeRangeDto> getTimeRanges(List<AvailableDateContext.TimeRangeContext> timeRanges) {
    return timeRanges.stream()
        .map(timeRange -> AvailableDateDto.TimeRangeDto.builder()
            .id(timeRange.getId())
            .value(TimeRangeUtils.mapTimeRange(timeRange.getValue()))
            .build())
        .toList();
  }

}
