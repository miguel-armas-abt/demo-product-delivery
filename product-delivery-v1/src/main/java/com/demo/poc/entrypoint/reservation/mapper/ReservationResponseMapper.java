package com.demo.poc.entrypoint.reservation.mapper;

import com.demo.poc.commons.config.di.MappingConfig;
import com.demo.poc.commons.states.context.availability.AvailableDateContext;
import com.demo.poc.commons.states.dto.response.ContextResponse;
import com.demo.poc.commons.utils.DateUtils;
import com.demo.poc.commons.utils.TimeRangeUtils;
import com.demo.poc.entrypoint.reservation.dto.response.AvailableDateDto;
import com.demo.poc.entrypoint.reservation.dto.response.ReservationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Comparator;
import java.util.List;

@Mapper(config = MappingConfig.class)
public interface ReservationResponseMapper {

  ReservationResponseDto toResponse(ContextResponse context);

  @Mapping(target = "availableDates", source = "availableDates", qualifiedByName = "mapAvailableDates")
  ReservationResponseDto toResponse(ContextResponse context, List<AvailableDateContext> availableDates);

  @Named("mapAvailableDates")
  static List<AvailableDateDto> mapAvailableDates(List<AvailableDateContext> availableDates) {
    return availableDates.stream()
        .sorted(Comparator.comparing(AvailableDateContext::getDate))
        .map(ReservationResponseMapper::mapAvailableDate)
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
