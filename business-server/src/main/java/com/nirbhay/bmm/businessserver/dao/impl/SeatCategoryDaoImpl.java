package com.nirbhay.bmm.businessserver.dao.impl;

import com.nirbhay.bmm.businessserver.dao.CityDao;
import com.nirbhay.bmm.businessserver.dao.SeatCategoryDao;
import com.nirbhay.bmm.businessserver.repository.CityRepository;
import com.nirbhay.bmm.businessserver.repository.SeatCategoryRepository;
import com.nirbhay.bmm.model.bs.City;
import com.nirbhay.bmm.model.bs.SeatCategory;
import com.nirbhay.bmm.model.exception.CityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.nirbhay.bmm.constants.CacheConstant.CITIES;
import static com.nirbhay.bmm.constants.CacheConstant.SEAT_CATEGORY_BY_ID;


@Component
@Slf4j
public class SeatCategoryDaoImpl implements SeatCategoryDao {
    private final SeatCategoryRepository repository;

    public SeatCategoryDaoImpl(SeatCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SeatCategory> findAll() {
        return repository.findAll();
    }
    @Override
    @CachePut(cacheNames = SEAT_CATEGORY_BY_ID, key = "#id")
    public SeatCategory saveCache(SeatCategory seatCategory, long id){
        log.info("Saving seat category to cache ");
        return seatCategory;
    }

    @Override
    @Cacheable(cacheNames = SEAT_CATEGORY_BY_ID, key = "#id")
    public SeatCategory findById(long id) {
        return repository.findById(id).orElse(null);
    }
}