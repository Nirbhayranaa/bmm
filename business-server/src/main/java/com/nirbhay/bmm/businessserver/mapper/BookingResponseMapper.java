package com.nirbhay.bmm.businessserver.mapper;

import com.nirbhay.bmm.model.bs.Booking;
import com.nirbhay.bmm.model.bs.BookingResponse;
import com.nirbhay.bmm.model.bs.BookingReservation;
import com.nirbhay.bmm.model.bs.Payment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nirbhay Rana
 */
public class BookingResponseMapper {

    public static BookingResponse mapBookingToDto(Booking booking, Payment payment) {
        BookingResponse.BookingResponseBuilder responseBuilder = BookingResponse.builder()
                .bookingId(booking.getId())
                .movie(booking.getMovieShow() != null ?
                        new BookingResponse.Movie(booking.getMovieShow().getMovieShowId(), booking.getMovieShow().getMovie().getMovieName(),
                                booking.getMovieShow().getShowDate(), booking.getMovieShow().getShowTimingFrom()) : null)
                .user(booking.getUser() != null ?
                        new BookingResponse.User(booking.getUser().getId(), booking.getUser().getUsername()) : null);

        List<BookingResponse.SeatDetails> seatDetails = booking.getBookingReservations() != null ?
                booking.getBookingReservations().stream()
                        .map(reservation -> new BookingResponse.SeatDetails(reservation.getSeat().getSeatName(), reservation.getSeat().getSeatCategory().getSeatCategoryName()))
                        .collect(Collectors.toList()) : Collections.emptyList();

        BookingResponse.Theater theater = booking.getMovieShow() != null && booking.getMovieShow().getScreen() != null && booking.getMovieShow().getScreen().getTheater() != null ?
                BookingResponse.Theater.builder()
                        .screenName(booking.getMovieShow().getScreen().getScreenName())
                        .theaterName(booking.getMovieShow().getScreen().getTheater().getTheaterName())
                        .theaterAddress(booking.getMovieShow().getScreen().getTheater().getAddress().getAddressLines())
                        .theaterCity(booking.getMovieShow().getScreen().getTheater().getAddress().getCity().getCityName())
                        .build() : null;
        if(payment!=null){
            BookingResponse.Payment payment1 = new BookingResponse.Payment(payment.getPaymentId(), payment.getActualAmount(), payment.getOfferAmount(), payment.getFinalAmount());
            responseBuilder.payment(payment1);
        }
        return responseBuilder.seatDetails(seatDetails)
                .theater(theater)
                .build();
    }

    public static BookingResponse mapBookingToDto(Booking booking) {
        BookingResponse.BookingResponseBuilder responseBuilder = BookingResponse.builder()
                .bookingId(booking.getId())
                .movie(booking.getMovieShow() != null ?
                        new BookingResponse.Movie(booking.getMovieShow().getMovieShowId(), booking.getMovieShow().getMovie().getMovieName(),
                                booking.getMovieShow().getShowDate(), booking.getMovieShow().getShowTimingFrom()) : null)
                .user(booking.getUser() != null ?
                        new BookingResponse.User(booking.getUser().getId(), booking.getUser().getUsername()) : null);

        List<BookingResponse.SeatDetails> seatDetails = booking.getBookingReservations() != null ?
                booking.getBookingReservations().stream()
                        .map(reservation -> new BookingResponse.SeatDetails(reservation.getSeat().getSeatName(), reservation.getSeat().getSeatCategory().getSeatCategoryName()))
                        .collect(Collectors.toList()) : Collections.emptyList();

        BookingResponse.Theater theater = booking.getMovieShow() != null && booking.getMovieShow().getScreen() != null && booking.getMovieShow().getScreen().getTheater() != null ?
                BookingResponse.Theater.builder()
                        .screenName(booking.getMovieShow().getScreen().getScreenName())
                        .theaterName(booking.getMovieShow().getScreen().getTheater().getTheaterName())
                        .theaterAddress(booking.getMovieShow().getScreen().getTheater().getAddress().getAddressLines())
                        .theaterCity(booking.getMovieShow().getScreen().getTheater().getAddress().getCity().getCityName())
                        .build() : null;

        return responseBuilder.seatDetails(seatDetails)
                .theater(theater)
                .build();
    }
}
