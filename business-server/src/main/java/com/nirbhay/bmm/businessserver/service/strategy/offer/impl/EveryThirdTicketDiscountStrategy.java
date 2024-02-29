package com.nirbhay.bmm.businessserver.service.strategy.offer.impl;

import com.nirbhay.bmm.businessserver.dao.SeatPriceDao;
import com.nirbhay.bmm.businessserver.service.strategy.offer.DiscountStrategy;
import com.nirbhay.bmm.model.bs.BookingReservation;
import com.nirbhay.bmm.model.bs.Offer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EveryThirdTicketDiscountStrategy implements DiscountStrategy {


    @Override
    public boolean isApplicable(Offer offer) {
        return offer.getOfferName().equalsIgnoreCase("third_ticket_50%_off");

    }

    @Override
    public void applyDiscount(BookingReservation bookingReservation, Offer offer) {

        int currentTicketIndex = bookingReservation.getTicketIndex();
        if ((currentTicketIndex) % 3 == 0) {
            BigDecimal calculatedDiscount = calculateDiscount(offer, bookingReservation.getCost());
            bookingReservation.setDiscountAmount(bookingReservation.getDiscountAmount().add(calculatedDiscount));
        }
    }
}
