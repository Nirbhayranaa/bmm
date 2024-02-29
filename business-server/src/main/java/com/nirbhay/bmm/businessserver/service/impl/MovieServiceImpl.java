package com.nirbhay.bmm.businessserver.service.impl;

import com.nirbhay.bmm.businessserver.dao.MovieDao;
import com.nirbhay.bmm.businessserver.service.MovieService;
import com.nirbhay.bmm.model.bs.Movie;
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
public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Optional<Movie> fetchMovieByMovieName(String movieName) {
        log.info("Fetching movie by movie name {}", movieName);
        try {
            return movieDao.fetchMovieByMovieName(movieName);
        } catch (Exception e) {
            log.error("Exception {} while fetching movie by name", e.getMessage());
            throw new InternalServerException(e.getLocalizedMessage());
        }

    }

    @Override
    public Movie addMovie(Movie movie) {
        log.info("Adding new movie by movie name {}", movie.getMovieName());
        try {
            return movieDao.save(movie);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause().getCause() instanceof PSQLException psqlException) {
                if ("23505".equals(psqlException.getSQLState())) {
                    // This is a unique constraint violation
                    throw new MovieAlreadyExistsException("Movie with name " + movie.getMovieName() + " already exists.");
                }
            }
            throw e;
        }
    }

    @Override
    public List<Movie> findAll() {
        log.info("Find all movies ");
        try {
            return movieDao.findAll();
        } catch (Exception e) {
            throw new InternalServerException("Error cause by" + e.getMessage());
        }
    }
}
