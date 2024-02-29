package com.nirbhay.bmm.businessserver.repository;

import com.nirbhay.bmm.model.bs.BookingReservation;
import com.nirbhay.bmm.model.bs.MovieShowSeatLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nirbhay Rana
 */
@Repository
public interface MovieShowSeatLockRepository extends JpaRepository<MovieShowSeatLock, Long> {
    long deleteByMovieShowIdAndSeatIdIn(Long movieShowId, Collection<Long> seatIds);

    List<MovieShowSeatLock> findAllByMovieShowIdAndSeatIdInAndLockExpirationTimeAfter(Long movieShowId, Set<Long> seatIds, LocalDateTime now);
}
