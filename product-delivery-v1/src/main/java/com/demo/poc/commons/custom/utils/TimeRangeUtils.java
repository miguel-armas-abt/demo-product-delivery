package com.demo.poc.commons.custom.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import com.demo.poc.commons.custom.states.context.availability.AvailableDateContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeRangeUtils {

    private static final String TIME_FORMAT_12H = "hh:mm a";
    private static final String TIME_FORMAT_24H = "H:mm";

    public static AvailableDateContext.TimeRangeContext createTimeRange(String timeRange) {
      return AvailableDateContext.TimeRangeContext.builder()
            .id(UUID.randomUUID().toString())
            .value(timeRange)
            .build();
    }

    public static String mapTimeRange(String timeRange) {
        String[] times = timeRange.split(" - ");
        LocalTime startTime = parseTime(times[0]);
        LocalTime endTime = parseTime(times[1]);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT_12H);
        return startTime.format(formatter) + " - " + endTime.format(formatter);
    }

    private static LocalTime parseTime(String time) {
        try {
            DateTimeFormatter formatter24H = DateTimeFormatter.ofPattern(TIME_FORMAT_24H);
            return LocalTime.parse(time, formatter24H);
        } catch (DateTimeParseException e) {
            DateTimeFormatter formatter12H = DateTimeFormatter.ofPattern(TIME_FORMAT_12H);
            return LocalTime.parse(time, formatter12H);
        }
    }
}
