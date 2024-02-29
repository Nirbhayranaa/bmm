package com.nirbhay.bmm.businessserver.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nirbhay.bmm.businessserver.dao.*;
import com.nirbhay.bmm.businessserver.service.MovieShowService;
import com.nirbhay.bmm.model.bs.*;
import com.nirbhay.bmm.model.exception.InternalServerException;
import com.nirbhay.bmm.model.exception.MovieNotFoundException;
import com.nirbhay.bmm.model.exception.TheaterNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class MovieShowServiceImpl implements MovieShowService {


    private final MovieShowDao movieShowDao;
    private final MovieDao movieDao;
    private final ScreenDao screenDao;

    private final SeatCategoryDao seatCategoryDao;
    private final SeatPriceDao seatPriceDao;

    public MovieShowServiceImpl(MovieShowDao movieShowDao, MovieDao movieDao, ScreenDao screenDao, SeatCategoryDao seatCategoryDao, SeatPriceDao seatPriceDao) {
        this.movieShowDao = movieShowDao;
        this.movieDao = movieDao;
        this.screenDao = screenDao;
        this.seatCategoryDao = seatCategoryDao;
        this.seatPriceDao = seatPriceDao;
    }

    @Override
    @Transactional
    public List<MovieShow> saveMovieShow(MovieShowRequest movieShowRequest) {
        log.info("Saving movie show {}", movieShowRequest);
        Movie movie = getMovie(movieShowRequest);
        Screen screen = getScreen(movieShowRequest);
        List<MovieShow> movieShows = createMovieShow(movieShowRequest, movie, screen);
        List<MovieShow> insertedMovieShow = movieShowDao.insertMovieShow(movieShows);
        createSeatPrices(movieShowRequest.getSeatPricesByCategory(), insertedMovieShow);
        return insertedMovieShow;

    }

    private static List<MovieShow> createMovieShow(MovieShowRequest movieShowRequest, Movie movie, Screen screen) {
        List<MovieShow> movieShows = new ArrayList<>();

        LocalDate startDate = movieShowRequest.getShowStartDate();
        LocalDate endDate = movieShowRequest.getShowEndDate();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            MovieShow movieShow = MovieShow.builder()
                    .movie(movie)
                    .screen(screen)
                    .showDate(date)
                    .showTimingFrom(movieShowRequest.getShowTimingFrom())
                    .showTimingTo(movieShowRequest.getShowTimingTo())
                    .build();
            movieShows.add(movieShow);
        }

        return movieShows;
    }

    private List<SeatPrice> createSeatPrices(Map<Long, BigDecimal> seatPricesByCategory, List<MovieShow> movieShows) {
        List<SeatPrice> seatPrices = new ArrayList<>();
        for (MovieShow movieShow : movieShows) {
            for (Map.Entry<Long, BigDecimal> entry : seatPricesByCategory.entrySet()) {
                SeatPrice seatPrice = new SeatPrice();
                seatPrice.setMovieShow(movieShow);
                seatPrice.setSeatCategory(getSeatCategoryById(entry.getKey()));
                seatPrice.setPrice(entry.getValue());
                seatPrices.add(seatPrice);
            }
        }
        seatPriceDao.saveAll(seatPrices);
        return seatPrices;
    }

    private SeatCategory getSeatCategoryById(Long key) {
        SeatCategory seatCategory = seatCategoryDao.findById(key);
        if (seatCategory == null) {
            throw new InternalServerException("Invalid seat category");
        }
        return seatCategory;
    }

    private Movie getMovie(MovieShowRequest movieShowRequest) {
        try {
            Optional<Movie> movie = movieDao.findById(movieShowRequest.getMovieId());
            return movie.orElseThrow(() -> new MovieNotFoundException("Movie is not found for id " + movieShowRequest.getMovieId()));
        } catch (Exception e) {
            log.error("Exception {} occurred while fetching movie", e.getMessage());
            throw new InternalServerException(e.getMessage());
        }
    }

    private Screen getScreen(MovieShowRequest movieShowRequest) {
        try {
            Optional<Screen> screen = screenDao.findById(movieShowRequest.getScreenId());
            return screen.orElseThrow(() -> new TheaterNotFoundException("Theater/Screen is not found for screen id " + movieShowRequest.getScreenId()));
        } catch (Exception e) {
            log.error("Exception {} occurred while fetching screen", e.getMessage());
            throw new InternalServerException(e.getMessage());
        }
    }


    @Override
    public List<MovieShow> fetchAllShowsByTheatreId(long theatreId) {
        return movieShowDao.fetchAllShowsByTheatreId(theatreId);
    }

    @Override
    public void deleteMovieShow(Long movieShow) {
        movieShowDao.logicalDelete(movieShow);
    }

}
