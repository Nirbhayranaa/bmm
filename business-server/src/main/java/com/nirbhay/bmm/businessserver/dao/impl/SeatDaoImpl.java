package com.nirbhay.bmm.businessserver.dao.impl;


import com.nirbhay.bmm.businessserver.dao.SeatDao;

import com.nirbhay.bmm.businessserver.repository.SeatRepository;
import com.nirbhay.bmm.model.bs.Seat;
import com.nirbhay.bmm.model.bs.SeatResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Component
@Slf4j
public class SeatDaoImpl implements SeatDao {
    private final SeatRepository repository;

    public SeatDaoImpl(SeatRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<SeatResponse> findAllSeatsByMovieShowId(long movieShowId) {
        log.info("Fetching all seats from db for movieShowId {}", movieShowId);
        return repository.findAllSeatsByMovieShowId(movieShowId);

    }

    @Override
    public List<Seat> findAllById(Set<Long> seatIds) {
        return repository.findAllById(seatIds);
    }
}