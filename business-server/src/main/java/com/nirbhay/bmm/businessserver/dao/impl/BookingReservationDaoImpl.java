package com.nirbhay.bmm.businessserver.dao.impl;

import com.nirbhay.bmm.businessserver.dao.BookingReservationDao;
import com.nirbhay.bmm.businessserver.repository.BookingReservationRepository;
import com.nirbhay.bmm.model.bs.BookingReservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Slf4j
public class BookingReservationDaoImpl implements BookingReservationDao {


    private final BookingReservationRepository repository;

    public BookingReservationDaoImpl(BookingReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<BookingReservation> findById(Long bookingId) {
        log.info("Fetching booking with ID: {}", bookingId);
        Optional<BookingReservation> booking = repository.findByBooking_Id(bookingId);
        return booking;
    }

    @Override
    @Transactional
    public List<BookingReservation> saveAll(Set<BookingReservation> reservationSet) {
        log.info("Saving booking reservation to db");
       return repository.saveAll(reservationSet);
    }
}
