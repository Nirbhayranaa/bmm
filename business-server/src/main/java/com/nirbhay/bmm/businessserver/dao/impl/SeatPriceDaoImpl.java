package com.nirbhay.bmm.businessserver.dao.impl;

import com.nirbhay.bmm.businessserver.dao.SeatPriceDao;
import com.nirbhay.bmm.businessserver.repository.SeatPriceRepository;
import com.nirbhay.bmm.model.bs.SeatPrice;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static com.nirbhay.bmm.constants.CacheConstant.SEAT_PRICE_BY_SEAT_CATEGORY_ID;

@Repository
public class SeatPriceDaoImpl implements SeatPriceDao {


    private final SeatPriceRepository repository;

    public SeatPriceDaoImpl(SeatPriceRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<SeatPrice> saveAll(List<SeatPrice> seatPrices) {
        return repository.saveAll(seatPrices);
    }

    @Override
    public List<SeatPrice> findAll() {
        return repository.findAll();
    }

    @Override
    @CachePut(cacheNames = SEAT_PRICE_BY_SEAT_CATEGORY_ID, key = "#cacheKey")
    public SeatPrice saveCache(SeatPrice seatPrice, Long cacheKey) {
        return seatPrice;
    }

    @Override
    @Cacheable(cacheNames = SEAT_PRICE_BY_SEAT_CATEGORY_ID, key = "#seatCategoryId")
    public SeatPrice findBySeatCategoryId(Long seatCategoryId) {
        return repository.findBySeatCategory_SeatCategoryId(seatCategoryId);
    }
}
