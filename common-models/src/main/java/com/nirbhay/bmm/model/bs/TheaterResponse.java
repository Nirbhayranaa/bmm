package com.nirbhay.bmm.model.bs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Nirbhay Rana
 */
@Data
@Builder
public class TheaterResponse {
    private long theaterId;
    private String theaterName;
    private String addressLines;
    private String primaryPhone;
    private String cityName;
    private String stateName;
    private long movieShowId;
    private LocalTime showTimingFrom;
    private LocalTime showTimingTo;
    private LocalDate showDate;
    private long screenId;
    private String screenName;
    private long movieId;
    private String movieName;
}
