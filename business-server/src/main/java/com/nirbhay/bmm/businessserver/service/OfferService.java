package com.nirbhay.bmm.businessserver.service;

import com.nirbhay.bmm.model.bs.BookingReservation;
import com.nirbhay.bmm.model.bs.Payment;

import java.util.List;

/**
 * @author Nirbhay Rana
 */
public interface OfferService {

    Payment applyOffer(List<BookingReservation> bookingReservationList);

}
