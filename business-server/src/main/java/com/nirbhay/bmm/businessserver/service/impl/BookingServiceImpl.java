package com.nirbhay.bmm.businessserver.service.impl;

import com.nirbhay.bmm.businessserver.dao.*;
import com.nirbhay.bmm.businessserver.mapper.BookingResponseMapper;
import com.nirbhay.bmm.businessserver.repository.PaymentRepository;
import com.nirbhay.bmm.businessserver.service.BookingService;
import com.nirbhay.bmm.businessserver.service.OfferService;
import com.nirbhay.bmm.model.bs.*;
import com.nirbhay.bmm.model.exception.InternalServerException;
import com.nirbhay.bmm.model.exception.MovieShowNotFoundException;
import com.nirbhay.bmm.model.exception.SeatAlreadyBookedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingReservationDao bookingReservationDao;
    private final MovieShowDao movieShowDao;

    private final BookingDao bookingDao;

    private final SeatDao seatDao;

    private final OfferService offerService;

    private final PaymentRepository paymentRepository;
    private final SeatPriceDao seatPriceDao;


    public BookingServiceImpl(BookingReservationDao bookingReservationDao, BookingDao bookingDao, MovieShowDao movieShowDao, SeatDao seatDao, OfferService offerService, PaymentRepository paymentRepository, SeatPriceDao seatPriceDao) {
        this.bookingReservationDao = bookingReservationDao;
        this.bookingDao = bookingDao;
        this.movieShowDao = movieShowDao;
        this.seatDao = seatDao;
        this.offerService = offerService;
        this.paymentRepository = paymentRepository;
        this.seatPriceDao = seatPriceDao;
    }

    @Override
    public BookingResponse findBookingById(Long bookingId) {
        log.info("Finding booking for id {}", bookingId);
        return bookingDao.findById(bookingId)
                .map(booking -> {
                    Optional<Payment> payment = paymentRepository.findByBooking_Id(bookingId);
                    return BookingResponseMapper.mapBookingToDto(booking, payment.orElse(null));
                })
                .orElse(null);


    }

    @Override
    @Transactional
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        long movieShowId = bookingRequest.getMovieShowId();
        MovieShow movieShow = getMovieShow(movieShowId);
        Booking booking = makeBooking(movieShow);
        List<BookingReservation> bookingReservations = makeReservation(bookingRequest, booking, movieShow);
        booking.setBookingReservations(bookingReservations);
        Payment payment = applyOffer(bookingReservations);
        payment.setBooking(booking);
        makePayment(payment);
        return BookingResponseMapper.mapBookingToDto(booking, payment);
    }

    private Payment applyOffer(List<BookingReservation> bookingReservations) {
        log.info("Applying offer");
        return offerService.applyOffer(bookingReservations);
    }

    private void makePayment(Payment payment) {
        log.info("Initiating payment");
        payment.setPaymentMethod("CARD");
        Payment save = paymentRepository.save(payment);
        log.info("Payment successful  {}", save);
    }

    private Booking makeBooking(MovieShow movieShow) {
        Booking booking = new Booking();
        booking.setBookingDate(LocalDate.now());
        booking.setMovieShow(movieShow);
        return bookingDao.save(booking);
    }

    private List<BookingReservation> makeReservation(BookingRequest bookingRequest, Booking booking, MovieShow movieShow) {
        Set<BookingReservation> allReservations = generateBookingReservation(bookingRequest, booking, movieShow);
        try {
            return bookingReservationDao.saveAll(allReservations);
        } catch (DataIntegrityViolationException e) {
            throw new SeatAlreadyBookedException();
        }
    }

    private Set<BookingReservation> generateBookingReservation(BookingRequest bookingRequest, Booking booking, MovieShow movieShow) {
        Set<BookingReservation> allReservations = new HashSet<>();
        List<Seat> seatList = seatDao.findAllById(bookingRequest.getSeatId());
        if (seatList.isEmpty()) {
            throw new InternalServerException("Invalid seat/s.");
        }
        AtomicInteger ticketIndex = new AtomicInteger(1);
        seatList.forEach(seat -> {
            BigDecimal cost = seatPriceDao.findBySeatCategoryId(seat.getSeatCategory().getSeatCategoryId()).getPrice();
            BookingReservation bookingReservation = new BookingReservation();
            bookingReservation.setBooking(booking);
            bookingReservation.setSeat(seat);
            bookingReservation.setMovieShow(movieShow);
            bookingReservation.setCost(cost);
            bookingReservation.setTicketIndex(ticketIndex.getAndIncrement());

            allReservations.add(bookingReservation);
        });
        return allReservations;
    }


    private MovieShow getMovieShow(long movieShowId) {
        try {
            return movieShowDao.findById(movieShowId).orElseThrow(() -> new MovieShowNotFoundException(movieShowId));
        } catch (MovieShowNotFoundException e) {
            log.error("Exception {}", e.getMessage());
            throw new InternalServerException(e.getMessage());
        }
    }
}
