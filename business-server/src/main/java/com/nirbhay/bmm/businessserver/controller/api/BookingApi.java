package com.nirbhay.bmm.businessserver.controller.api;

import com.nirbhay.bmm.model.bs.BookingRequest;
import com.nirbhay.bmm.model.bs.BookingResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Booking Management API")
public interface BookingApi {

    @ApiOperation(value = "Find booking by ID", notes = "Retrieves a booking by its ID")
    @GetMapping(value = "/api/v1/booking/{bookingId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking found"),
            @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    ResponseEntity<BookingResponse> findBookingById(@PathVariable("bookingId") Long bookingId);

    @ApiOperation(value = "Create new booking", notes = "Create a new booking in the system")
    @PostMapping(value = "/api/v1/booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Booking created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest);
}
