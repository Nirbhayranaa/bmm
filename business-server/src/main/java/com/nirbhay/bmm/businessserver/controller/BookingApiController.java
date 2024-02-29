package com.nirbhay.bmm.businessserver.controller;

import com.nirbhay.bmm.businessserver.controller.api.BookingApi;
import com.nirbhay.bmm.businessserver.lock.DbLockManager;
import com.nirbhay.bmm.businessserver.lock.LockManager;
import com.nirbhay.bmm.businessserver.service.BookingService;
import com.nirbhay.bmm.model.bs.BookingRequest;
import com.nirbhay.bmm.model.bs.BookingResponse;
import com.nirbhay.bmm.model.exception.SeatAlreadyBookedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@Slf4j
public class BookingApiController implements BookingApi {

    private final BookingService bookingService;
    private final LockManager lockManager;

    public BookingApiController(BookingService bookingService, DbLockManager lockManager) {
        this.bookingService = bookingService;
        this.lockManager = lockManager;
    }

    @Override
    public ResponseEntity<BookingResponse> findBookingById(Long bookingId) {
        log.info("Request received for fetch booking for id {}", bookingId);
        BookingResponse booking = bookingService.findBookingById(bookingId);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<BookingResponse> createBooking(BookingRequest bookingRequest) {
        log.info("Request received to create booking {}", bookingRequest);
        try {
            acquireLock(bookingRequest);
            BookingResponse booking = bookingService.createBooking(bookingRequest);

            return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        } finally {
            log.info("Releasing the lock.");
            releaseLock(bookingRequest);
        }

    }

    private void releaseLock(BookingRequest bookingRequest) {

        lockManager.releaseLock(bookingRequest.getMovieShowId(), bookingRequest.getSeatId());
    }

    private void acquireLock(BookingRequest bookingRequest) {
        long movieShowId = bookingRequest.getMovieShowId();

        log.info("Attempt to acquire locks for movie show {} and seats {}", movieShowId, bookingRequest.getSeatId());
        boolean movieShowLockAcquired = lockManager.acquireLock(movieShowId, bookingRequest.getSeatId(), Duration.ofMinutes(5));
        if (!movieShowLockAcquired) {
            throw new SeatAlreadyBookedException();
        }
    }
}
