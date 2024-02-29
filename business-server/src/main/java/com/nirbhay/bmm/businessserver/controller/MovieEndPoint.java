package com.nirbhay.bmm.businessserver.controller;

import com.nirbhay.bmm.businessserver.controller.api.MovieApi;
import com.nirbhay.bmm.businessserver.controller.api.MovieShowApi;
import com.nirbhay.bmm.businessserver.service.MovieService;
import com.nirbhay.bmm.businessserver.service.MovieShowService;
import com.nirbhay.bmm.businessserver.service.ScreenService;
import com.nirbhay.bmm.model.bs.Movie;
import com.nirbhay.bmm.model.bs.MovieShow;
import com.nirbhay.bmm.model.bs.Screen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class MovieEndPoint implements MovieApi {


	private final MovieService movieService;

    public MovieEndPoint(MovieService movieService) {
        this.movieService = movieService;
    }


    @Override
	public ResponseEntity<Movie> fetchMovieByMovieName(String movieName) {
		log.info("Request received for fetch by movie {}",movieName);
		Optional<Movie> optionalMovie = movieService.fetchMovieByMovieName(movieName);
		return optionalMovie
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
	}


	@Override
	public ResponseEntity<Movie> addMovie(Movie movie) {
		log.info("Request received for add new movie {}",movie);
		Movie addedMovie = movieService.addMovie(movie);
		return ResponseEntity.ok(addedMovie);
	}

	@Override
	public ResponseEntity<List<Movie>> findAllMovies() {
		log.info("Request received for find all movie");
		return ResponseEntity.ok(movieService.findAll());
	}

}