package com.nirbhay.bmm.businessserver.repository;

import com.nirbhay.bmm.model.bs.Seat;
import com.nirbhay.bmm.model.bs.SeatResponse;
import jakarta.persistence.ConstructorResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
//    @Query("SELECT NEW com.nirbhay.bmm.model.bs.SeatResponse(" +
//            "s.id, s.seatName, sc.seatCategoryId, sc.seatCategoryName, " +
//            "CASE WHEN br.id IS NOT NULL THEN TRUE ELSE FALSE END) " +
//            "FROM Seat s " +
//            "JOIN s.seatCategory sc " +
//            "LEFT JOIN BookingReservation br ON br.seat.id = s.id AND br.booking.movieShow.movieShowId= :movieShowId")
//    List<SeatResponse> findAllSeatsByMovieShowId(@Param("movieShowId") long movieShowId);
@Query("SELECT NEW com.nirbhay.bmm.model.bs.SeatResponse(" +
        "s.id, s.seatName, sc.seatCategoryId, sc.seatCategoryName, " +
        "CASE WHEN br.id IS NOT NULL OR mssl.seatId IS NOT NULL THEN TRUE ELSE FALSE END) " +
        "FROM Seat s " +
        "JOIN s.seatCategory sc " +
        "LEFT JOIN BookingReservation br ON br.seat.id = s.id AND br.booking.movieShow.movieShowId = :movieShowId AND br.isDeleted = 0 " +
        "LEFT JOIN MovieShowSeatLock mssl ON mssl.seatId = s.id AND mssl.movieShowId = :movieShowId AND mssl.lockExpirationTime > CURRENT_TIMESTAMP")
List<SeatResponse> findAllSeatsByMovieShowId(@Param("movieShowId") long movieShowId);


}
