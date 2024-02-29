package com.nirbhay.bmm.businessserver.service;

import com.nirbhay.bmm.model.bs.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Optional<Movie> fetchMovieByMovieName(String movieName);
    Movie addMovie(Movie movie);

    List<Movie> findAll();
}
