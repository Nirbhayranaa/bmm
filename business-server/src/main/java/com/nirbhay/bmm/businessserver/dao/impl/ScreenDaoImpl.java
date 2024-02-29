package com.nirbhay.bmm.businessserver.dao.impl;

import com.nirbhay.bmm.businessserver.dao.MovieDao;
import com.nirbhay.bmm.businessserver.dao.ScreenDao;
import com.nirbhay.bmm.businessserver.repository.MovieRepository;
import com.nirbhay.bmm.businessserver.repository.ScreenRepository;
import com.nirbhay.bmm.model.bs.Movie;
import com.nirbhay.bmm.model.bs.Screen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class ScreenDaoImpl implements ScreenDao {

    private final ScreenRepository screenRepository;

    public ScreenDaoImpl(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }

    @Override
    public List<Screen> fetchAllScreensByTheatreId(long theatreId) {
        log.info("Fetching all screens from db by theater id {}", theatreId);
        return screenRepository.fetchAllScreensByTheatreId(theatreId);
    }

    @Override
    public Optional<Screen> findById(long screenId) {
        log.info("Fetching  screen from db by id {}", screenId);
        return screenRepository.findById(screenId);
    }
}