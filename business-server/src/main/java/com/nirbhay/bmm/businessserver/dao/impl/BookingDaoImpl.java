package com.nirbhay.bmm.businessserver.dao.impl;

import com.nirbhay.bmm.businessserver.dao.BookingDao;
import com.nirbhay.bmm.businessserver.repository.BookingRepository;
import com.nirbhay.bmm.model.bs.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
public class BookingDaoImpl implements BookingDao {


    private final BookingRepository repository;

    public BookingDaoImpl(BookingRepository repository) {
        this.repository = repository;
    }


    @Override
    public Booking save(Booking booking) {
        return repository.save(booking);
    }

    @Override
    public Optional<Booking> findById(Long bookingId) {
        return repository.findByIdAndIsDeleted(bookingId,0);
    }
}
