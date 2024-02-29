package com.nirbhay.bmm.businessserver.controller;

import com.nirbhay.bmm.businessserver.controller.api.SeatApi;
import com.nirbhay.bmm.businessserver.service.SeatService;
import com.nirbhay.bmm.model.bs.SeatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class SeatEndPoint implements SeatApi {
	private final SeatService service;

    public SeatEndPoint(SeatService service) {
        this.service = service;
    }


    @Override
	public ResponseEntity<List<SeatResponse>> findSeatsByMovieShowId(Long movieShowId) {
		log.info("Request received to fetch seats for movieShowId {}", movieShowId);
		List<SeatResponse> seatResponses = service.findALlSeatsByMovieShowId(movieShowId);
		return ResponseEntity.ok(seatResponses);
	}
}