package com.nirbhay.bmm.businessserver.service;

import com.nirbhay.bmm.model.bs.Movie;
import com.nirbhay.bmm.model.bs.SeatResponse;

import java.util.List;
import java.util.Optional;

public interface SeatService {

public List<SeatResponse> findALlSeatsByMovieShowId(long movieShowId);

}
