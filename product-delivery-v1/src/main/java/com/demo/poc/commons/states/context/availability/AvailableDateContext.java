package com.demo.poc.commons.states.context.availability;

import java.io.Serializable;
import java.util.List;

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
public class AvailableDateContext implements Serializable {

  private String date;
  private List<TimeRangeContext> timeRanges;

  @Builder
  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class TimeRangeContext implements Serializable {
    private String id;
    private String value;
  }
}
