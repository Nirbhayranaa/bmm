package com.nirbhay.bmm.businessserver.lock;

import com.nirbhay.bmm.businessserver.repository.MovieShowSeatLockRepository;
import com.nirbhay.bmm.model.bs.MovieShowSeatLock;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class DbLockManager implements LockManager{

    @Autowired
    private MovieShowSeatLockRepository lockRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public synchronized boolean acquireLock(Long movieShowId, Set<Long> seatIds, Duration lockDuration) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lockExpirationTime = now.plus(lockDuration);
        List<MovieShowSeatLock> locksToSave = new ArrayList<>();
        for (Long seatId : seatIds) {
            MovieShowSeatLock lock = new MovieShowSeatLock();
            lock.setMovieShowId(movieShowId);
            lock.setSeatId(seatId);
            lock.setLockExpirationTime(lockExpirationTime);
            locksToSave.add(lock);
        }
        try {
            List<MovieShowSeatLock> conflictingLocks = lockRepository
                    .findAllByMovieShowIdAndSeatIdInAndLockExpirationTimeAfter(movieShowId, seatIds, LocalDateTime.now());

            if (!conflictingLocks.isEmpty()) {
                log.info("Lock already exists for at least one seat, unable to acquire");
                return false;
            }
            lockRepository.saveAllAndFlush(locksToSave);
            log.info("Acquired locks for seats: {}", seatIds);
            return true;
        } catch (DataIntegrityViolationException e) {
            log.error("Failed to acquire lock: {}", e.getMessage());
            return false;
        }
    }

    @Transactional
    public synchronized void releaseLock(Long movieShowId,  Set<Long> seatIds) {
        lockRepository.deleteByMovieShowIdAndSeatIdIn(movieShowId, seatIds);
        entityManager.flush();
        log.info("All locks released successfully");
    }
}
