package com.nirbhay.bmm.businessserver.dao;

import com.nirbhay.bmm.model.bs.BookingReservation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookingReservationDao {
    Optional<BookingReservation> findById(Long bookingId);
    List<BookingReservation> saveAll(Set<BookingReservation> reservations);
}
