package com.nirbhay.bmm.businessserver.controller.api;

import com.nirbhay.bmm.model.bs.SeatResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(value = "Seat Management API")
public interface SeatApi {


    @ApiOperation(value = "Fetch seats for", notes = "Retrieves a movie by its name")
    @GetMapping(value = "/api/v1/seat/{movieShowId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Movie found"),
            @ApiResponse(code = 404, message = "Movie not found")
    })
    ResponseEntity<List<SeatResponse>> findSeatsByMovieShowId(@PathVariable("movieShowId") Long movieShowId);


}

