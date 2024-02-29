package com.nirbhay.bmm.businessserver.service.impl;

import com.nirbhay.bmm.businessserver.dao.MovieDao;
import com.nirbhay.bmm.businessserver.dao.SeatDao;
import com.nirbhay.bmm.businessserver.service.MovieService;
import com.nirbhay.bmm.businessserver.service.SeatService;
import com.nirbhay.bmm.model.bs.Movie;
import com.nirbhay.bmm.model.bs.SeatResponse;
import com.nirbhay.bmm.model.exception.InternalServerException;
import com.nirbhay.bmm.model.exception.MovieAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class SeatServiceImpl implements SeatService {

    private final SeatDao seatDao;

    public SeatServiceImpl(SeatDao seatDao) {
        this.seatDao = seatDao;
    }

    @Override
    public List<SeatResponse> findALlSeatsByMovieShowId(long movieShowId) {
        log.info("Fetching all seats for movieShowId {}", movieShowId);
        return seatDao.findAllSeatsByMovieShowId(movieShowId);
    }
}
