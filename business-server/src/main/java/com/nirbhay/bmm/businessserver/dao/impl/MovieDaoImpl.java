package com.nirbhay.bmm.businessserver.dao.impl;

import com.nirbhay.bmm.businessserver.dao.MovieDao;
import com.nirbhay.bmm.businessserver.repository.MovieRepository;
import com.nirbhay.bmm.model.bs.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class MovieDaoImpl implements MovieDao {
	private final MovieRepository movieRepository;

    public MovieDaoImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
	public Optional<Movie> fetchMovieByMovieName(String movieName) {
		log.info("Fetching movie from db for movie name {}", movieName);
		return movieRepository.findByMovieName(movieName.toLowerCase());
	}

	@Override
	@Transactional
	@Modifying
	public Movie save(Movie movie) {
		log.info("Adding new movie to db");
		return movieRepository.save(movie);
	}

	@Override
	public Optional<Movie> findById(long movieId) {
		log.info("Finding movie from db by id {}",movieId);
		return movieRepository.findById(movieId);
	}

	@Override
	public List<Movie> findAll() {
		log.info("Finding all movies from db");
		return movieRepository.findAll();
	}
}