package com.nirbhay.bmm.businessserver.service;

import com.nirbhay.bmm.model.bs.BookingResponse;
import com.nirbhay.bmm.model.bs.BookingRequest;

public interface BookingService {
    BookingResponse findBookingById(Long bookingId);
    BookingResponse createBooking(BookingRequest bookingRequest);
}
