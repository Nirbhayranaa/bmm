package com.nirbhay.bmm.businessserver.controller;

import java.util.List;

import com.nirbhay.bmm.businessserver.controller.api.MovieShowApi;
import com.nirbhay.bmm.businessserver.service.MovieShowService;
import com.nirbhay.bmm.businessserver.service.ScreenService;
import com.nirbhay.bmm.model.bs.Movie;
import com.nirbhay.bmm.model.bs.MovieShow;
import com.nirbhay.bmm.model.bs.MovieShowRequest;
import com.nirbhay.bmm.model.bs.Screen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MovieShowEndPoint implements MovieShowApi {
	
	private final MovieShowService movieShowService;

	private final ScreenService screenService;

    public MovieShowEndPoint(MovieShowService movieShowService, ScreenService screenService) {
        this.movieShowService = movieShowService;
        this.screenService = screenService;
    }

    @Override
	public ResponseEntity<List<MovieShow>> saveMovieShow(@Validated MovieShowRequest movieShowRequest)  {
		log.info("Request received for adding movie show for movie id {} on screen id {}", movieShowRequest.getMovieId(), movieShowRequest.getScreenId());
		List<MovieShow> movieShows = movieShowService.saveMovieShow(movieShowRequest);
		return ResponseEntity.ok(movieShows);
	}

	@Override
	public ResponseEntity<String> deleteMovieShow(Long movieShowid){
		movieShowService.deleteMovieShow(movieShowid);
		return ResponseEntity.ok("Successful");
	}


	@Override
	public ResponseEntity<List<Screen>> fetchAllScreensByTheatreId(Long theatreId)  {
		List<Screen> li = screenService.fetchAllScreensByTheatreId(theatreId);
		return ResponseEntity.ok(li);
	}

	@Override
	public ResponseEntity<List<MovieShow>> fetchAllShowsByTheatreId(Long theatreId) {
		List<MovieShow> li = movieShowService.fetchAllShowsByTheatreId(theatreId);
		return ResponseEntity.ok(li);
	}


}