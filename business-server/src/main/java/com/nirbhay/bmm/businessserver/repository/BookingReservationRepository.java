package com.nirbhay.bmm.businessserver.repository;

import com.nirbhay.bmm.model.bs.Booking;
import com.nirbhay.bmm.model.bs.BookingReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Nirbhay Rana
 */
@Repository
public interface BookingReservationRepository extends JpaRepository<BookingReservation, Long> {
    Optional<BookingReservation> findByBooking_Id(Long id);


    @Transactional
    @Modifying
    @Query("UPDATE BookingReservation br SET br.isDeleted = 1 WHERE br.id IN :reservationIds")
    int bulkCancelReservations(List<Long> reservationIds);
}
