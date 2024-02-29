package com.nirbhay.bmm.businessserver.controller.api;

import com.nirbhay.bmm.model.bs.Movie;
import com.nirbhay.bmm.model.bs.MovieShow;
import com.nirbhay.bmm.model.bs.Screen;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(value = "Movie Management API")
public interface MovieApi {


    @ApiOperation(value = "Fetch movie by name", notes = "Retrieves a movie by its name")
    @GetMapping(value = "/api/ext/v1/movie/{movieName}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Movie found"),
            @ApiResponse(code = 404, message = "Movie not found")
    })
    ResponseEntity<Movie> fetchMovieByMovieName(@PathVariable("movieName") String movieName) throws Exception;


    @ApiOperation(value = "Add new movie", notes = "Add new movie to system")
    @PostMapping(value = "/api/v1/movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Add new movie"),
    })
    ResponseEntity<Movie> addMovie(@RequestBody Movie movie);


    @ApiOperation(value = "Find all movies", notes = "Find all movies")
    @GetMapping(value = "/api/v1/movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All movies"),
    })
    ResponseEntity<List<Movie>> findAllMovies();
}

