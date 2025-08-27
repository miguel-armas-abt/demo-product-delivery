package com.demo.poc.commons.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

  public static final String DATE_SEPARATOR = ";";
  private static final String DATE_PATTERN = "yyyyMMdd";
  private static final String LANGUAGE_ES = "es";
  private static final String ZERO = "0";

  public static String mapDate(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    LocalDate localDate = LocalDate.parse(date, formatter);
    return getDayAbbreviation(localDate) + DATE_SEPARATOR
        + prettyDayOfMonth(localDate.getDayOfMonth()) + DATE_SEPARATOR
        + getMonthName(localDate) + DATE_SEPARATOR
        + localDate.getYear();
  }

  private static String getDayAbbreviation(LocalDate localDate) {
    String abbreviation = localDate
        .getDayOfWeek()
        .getDisplayName(TextStyle.SHORT, new Locale(LANGUAGE_ES));
    return abbreviation.substring(0, 1).toUpperCase() + abbreviation.substring(1);
  }

  private static String getMonthName(LocalDate localDate) {
    String monthName = localDate
        .getMonth()
        .getDisplayName(TextStyle.FULL, new Locale(LANGUAGE_ES));
    return monthName.substring(0, 1).toUpperCase() + monthName.substring(1);
  }

  private static String prettyDayOfMonth(int  dayOfMonth) {
    return dayOfMonth < 10
        ? ZERO + dayOfMonth
        : String.valueOf(dayOfMonth);
  }
}
