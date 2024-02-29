package com.nirbhay.bmm.businessserver.repository;

import com.nirbhay.bmm.model.bs.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Nirbhay Rana
 */
@Repository
public interface MovieShowRepository extends JpaRepository<MovieShow, Long> {

    @Query("SELECT ms FROM MovieShow ms JOIN ms.screen s JOIN s.theater t WHERE ms.isDeleted =0 AND t.id = :theaterId AND ms.showDate >= CURRENT_DATE")
    List<MovieShow> findAllByTheaterId(long theaterId);

    @Transactional
    @Modifying
    @Query("UPDATE MovieShow m " +
            "SET m.isDeleted = 1 " +
            "WHERE m.movieShowId = ?1 " +
            "AND NOT EXISTS (SELECT 1 FROM BookingReservation br WHERE br.booking.movieShow = m)")
    int logicalDelete(Long id);

    Optional<MovieShow> findByMovieShowIdAndIsDeleted(Long movieShowId, int isDeleted);


}
