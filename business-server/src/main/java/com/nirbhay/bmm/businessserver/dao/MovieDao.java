package com.nirbhay.bmm.businessserver.dao;

import com.nirbhay.bmm.model.bs.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDao {

	Optional<Movie> fetchMovieByMovieName(String movieName);

    Movie save(Movie movie);

    Optional<Movie> findById(long movieId);

    List<Movie> findAll();
}