package com.nirbhay.bmm.model.bs;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;


/**
 * @author Nirbhay Rana
 */
@Data
public class MovieShowRequest {
    private Long movieShowId;
    @NotNull
    private long movieId;

    @NotNull
    private long screenId;

    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull
    private LocalTime showTimingFrom;

    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull
    private LocalTime showTimingTo;

    @NotNull
    @Future
    private LocalDate showStartDate;

    @NotNull
    @Future
    private LocalDate showEndDate;

    @NotEmpty
    private Map<@NotNull Long, @NotNull BigDecimal> seatPricesByCategory;
}
