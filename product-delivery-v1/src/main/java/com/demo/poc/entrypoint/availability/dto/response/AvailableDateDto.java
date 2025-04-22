package com.demo.poc.entrypoint.availability.dto.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailableDateDto implements Serializable {

  private DateDto date;
  private List<TimeRangeDto> timeRanges;

  @Builder
  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class DateDto implements Serializable {
    private DayDto day;
    private String month;
    private String year;
  }

  @Builder
  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class DayDto implements Serializable {
    private String abbreviation;
    private String number;
  }

  @Builder
  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class TimeRangeDto implements Serializable {
    private String id;
    private String value;
  }
}
