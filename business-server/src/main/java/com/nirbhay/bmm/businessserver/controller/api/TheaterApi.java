package com.nirbhay.bmm.businessserver.controller.api;

import com.nirbhay.bmm.model.bs.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(value = "Theater Management API")
public interface TheaterApi {

    @ApiOperation(value = "Create a theater", notes = "Creates a new theater")
    @PostMapping(value = "/api/v1/theaters")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Theater created successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 409, message = "Theater already exists")
    })
    ResponseEntity<Theater> createTheater(@RequestBody Theater theater) throws Exception;

    @ApiOperation(value = "Search theaters", notes = "Searches for theaters based on criteria")
    @PostMapping(value = "/api/ext/v1/theaters/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    ResponseEntity<List<TheaterResponse>> fetchAllTheatreBySearchCriteria(@RequestBody SearchCriteria searchCriteria);

    @ApiOperation(value = "add screen", notes = "add screen for theater")
    @PostMapping(value = "/api/v1/theaters/screen")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    ResponseEntity<Screen> addScreen(@RequestBody ScreenAddRequest screenAddRequest);

}
