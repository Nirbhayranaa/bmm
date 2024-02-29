package com.nirbhay.bmm.model.bs;

import com.nirbhay.bmm.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
public class SearchCriteria {

    private LocalTime showStartTiming;

    private LocalDate showDate;

    private String city;

    private String movieName;

    private String language;
}