package com.nirbhay.bmm.businessserver.dao;

import com.nirbhay.bmm.model.bs.City;
import com.nirbhay.bmm.model.bs.Seat;
import com.nirbhay.bmm.model.bs.SeatResponse;

import java.util.List;
import java.util.Set;

public interface SeatDao {
	List<SeatResponse> findAllSeatsByMovieShowId(long movieShowId);

	List<Seat> findAllById(Set<Long> seatIds);
}
