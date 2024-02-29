package com.nirbhay.bmm.businessserver.dao;

import com.nirbhay.bmm.model.bs.SeatPrice;

import java.util.List;

public interface SeatPriceDao {
    List<SeatPrice> saveAll(List<SeatPrice> seatPrices);
    List<SeatPrice> findAll();

    SeatPrice saveCache(SeatPrice seatPrice, Long cacheKey);

    SeatPrice findBySeatCategoryId(Long seatCategoryId);
}
