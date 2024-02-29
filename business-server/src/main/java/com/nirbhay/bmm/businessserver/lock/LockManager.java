package com.nirbhay.bmm.businessserver.lock;

import java.time.Duration;
import java.util.Set;

public interface LockManager {
    boolean acquireLock(Long movieShowId, Set<Long> seatId, Duration duration);
    void releaseLock(Long movieShowId, Set<Long> seatId);
}
