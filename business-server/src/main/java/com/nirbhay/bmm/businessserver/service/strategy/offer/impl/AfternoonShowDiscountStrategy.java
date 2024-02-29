package com.nirbhay.bmm.businessserver.service.strategy.offer.impl;

import com.nirbhay.bmm.businessserver.dao.SeatPriceDao;
import com.nirbhay.bmm.businessserver.service.strategy.offer.DiscountStrategy;
import com.nirbhay.bmm.model.bs.BookingReservation;
import com.nirbhay.bmm.model.bs.Offer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;

@Component
public class AfternoonShowDiscountStrategy implements DiscountStrategy {


    @Override
    public boolean isApplicable(Offer offer) {
        return offer.getOfferName().equalsIgnoreCase("afternoon_20%_off");
    }

    @Override
    public void applyDiscount(BookingReservation bookingReservation, Offer offer) {
        LocalTime showTime = bookingReservation.getBooking().getMovieShow().getShowTimingFrom();
        if (isAfternoonShow(showTime)) {
            BigDecimal calculatedDiscount = calculateDiscount( offer, bookingReservation.getCost());
            bookingReservation.setDiscountAmount(bookingReservation.getDiscountAmount().add(calculatedDiscount));
        }
    }

    private boolean isAfternoonShow(LocalTime showTime) {
        return showTime.getHour() >= 12 && showTime.getHour() < 18;
    }
}
