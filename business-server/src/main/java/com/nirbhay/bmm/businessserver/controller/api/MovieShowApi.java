package com.nirbhay.bmm.businessserver.controller.api;

import com.nirbhay.bmm.model.bs.Movie;
import com.nirbhay.bmm.model.bs.MovieShow;
import com.nirbhay.bmm.model.bs.MovieShowRequest;
import com.nirbhay.bmm.model.bs.Screen;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Movie Show Management API")
public interface MovieShowApi {

    @ApiOperation(value = "Save a movie show", notes = "Creates a new movie show")
    @PostMapping(value = "/api/v1/movieshow")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Movie show created successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 409, message = "Movie show already exists")
    })
    ResponseEntity<List<MovieShow>> saveMovieShow(@RequestBody MovieShowRequest movieShowRequest);

    @ApiOperation(value = "Delete a movie show", notes = "Deletes an existing movie show")
    @DeleteMapping(value = "/api/v1/movieshow/{movieShowId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Movie show deleted successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Movie show not found")
    })
    ResponseEntity<String> deleteMovieShow(@PathVariable("movieShowId") Long movieShowId) throws Exception;

    @ApiOperation(value = "Fetch all screens by theater ID", notes = "Retrieves all screens associated with a theater")
    @GetMapping(value = "/api/v1/fetch/screens/{theatreId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of screens retrieved"),
            @ApiResponse(code = 404, message = "Theater not found")
    })
    ResponseEntity<List<Screen>> fetchAllScreensByTheatreId(@PathVariable("theatreId") Long theatreId) throws Exception;

    @ApiOperation(value = "Fetch all shows by theater ID", notes = "Retrieves all movie shows associated with a theater")
    @GetMapping(value = "/api/v1/shows/{theatreId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of movie shows retrieved"),
            @ApiResponse(code = 404, message = "Theater not found")
    })
    ResponseEntity<List<MovieShow>> fetchAllShowsByTheatreId(@PathVariable("theatreId") Long theatreId) throws Exception;


}

