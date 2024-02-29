package com.nirbhay.bmm.model.bs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Nirbhay Rana
 */
@Data
@Builder
public class BookingResponse {
    private long bookingId;
    private Movie movie;
    private Theater theater;
    private List<SeatDetails> seatDetails;
    private User user;
    private Payment payment;


    @Data
    @AllArgsConstructor
    public static class Payment {
        long paymentId;
        BigDecimal actualAmount;
        BigDecimal offerAmount;
        BigDecimal finalAmount;
    }

    @Data
    @AllArgsConstructor
    public static class Movie {
        long movieShowId;
        String name;
        LocalDate date;
        LocalTime time;
    }

    @Data
    @Builder
    public static class Theater {
        private String screenName;
        private String theaterName;
        private String theaterAddress;
        private String theaterCity;
    }

    @Data
    @AllArgsConstructor
    public static class SeatDetails {
        String seat;
        String category;
    }

    @Data
    @AllArgsConstructor
    public static class User {
        long id;
        String name;
    }
}
