package com.demo.poc.commons.utils;

import com.demo.poc.entrypoint.reservation.exceptions.NoSuchTimeRangeIdException;
import com.demo.poc.commons.states.context.availability.AvailableDateContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeRangeSelectorUtil {

  public static Map.Entry<String,String> searchDateAndTimeRange(String timeRangeId,
                                                                List<AvailableDateContext> availableDates) {
    AvailableDateContext dateContext = availableDates.stream()
        .filter(availableDate -> availableDate.getTimeRanges().stream().anyMatch(timeRange -> timeRangeId.equals(timeRange.getId())))
        .findFirst()
        .orElseThrow(() -> new NoSuchTimeRangeIdException(timeRangeId));

    String selectedDate = dateContext.getDate();

    String selectedTimeRange = dateContext.getTimeRanges().stream()
        .filter(timeRange -> timeRangeId.equals(timeRange.getId()))
        .map(AvailableDateContext.TimeRangeContext::getValue)
        .findFirst()
        .orElseThrow(() -> new NoSuchTimeRangeIdException(timeRangeId));

    return Map.of(selectedDate, selectedTimeRange).entrySet().iterator().next();
  }
}
