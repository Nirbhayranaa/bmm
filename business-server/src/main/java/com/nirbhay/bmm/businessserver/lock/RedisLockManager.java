package com.nirbhay.bmm.businessserver.lock;


import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Set;

@Component
public class RedisLockManager implements LockManager {

    private static final String LOCK_PREFIX = "seat-lock:";
    private static final String LOCK_VALUE = "locked";

    //private final RedisTemplate<String, String> redisTemplate;

//    @Autowired
//    public RedisLockManager(RedisTemplate<String, String> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }

    @Override
    public boolean acquireLock(Long seatId, Set<Long> userId, Duration duration){
        String key = LOCK_PREFIX + seatId;
        //boolean lockAcquired = redisTemplate.opsForValue().setIfAbsent(key, LOCK_VALUE, duration.toMillis(), TimeUnit.MILLISECONDS);
        return true;
    }

    @Override
    public void releaseLock(Long seatId, Set<Long> userId) {
        String key = LOCK_PREFIX + seatId;
        //redisTemplate.delete(key);
    }
}
