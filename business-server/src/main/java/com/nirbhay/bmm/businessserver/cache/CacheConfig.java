package com.nirbhay.bmm.businessserver.cache;

import com.nirbhay.bmm.constants.CacheConstant;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig implements CacheConstant {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(CITIES,
                THEATER_BY_CITY,
                SEAT_CATEGORY_BY_ID,
                SEAT_PRICE_BY_SEAT_CATEGORY_ID);
    }

}
