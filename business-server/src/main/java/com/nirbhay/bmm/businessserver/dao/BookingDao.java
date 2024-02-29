package com.nirbhay.bmm.businessserver.dao;

import com.nirbhay.bmm.model.bs.Booking;

import java.util.Optional;

public interface BookingDao {

    Booking save(Booking booking);

    Optional<Booking> findById(Long bookingId);

}
