package com.nirbhay.bmm.businessserver.dao;

import com.nirbhay.bmm.model.bs.Seat;
import com.nirbhay.bmm.model.bs.SeatCategory;
import com.nirbhay.bmm.model.bs.SeatResponse;

import java.util.List;
import java.util.Set;

public interface SeatCategoryDao {
	List<SeatCategory> findAll();
	SeatCategory saveCache(SeatCategory seatCategory, long id);
	SeatCategory findById(long id);
}
