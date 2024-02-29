package com.nirbhay.bmm.businessserver.repository;

import com.nirbhay.bmm.model.bs.Booking;
import com.nirbhay.bmm.model.bs.BookingReservation;
import com.nirbhay.bmm.model.bs.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Nirbhay Rana
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Booking b SET b.isDeleted = 1 WHERE b.id IN :bookingIds")
    int bulkCancelBookings(List<Long> bookingIds);

    Optional<Booking> findByIdAndIsDeleted(Long id, int isDeleted);


}
