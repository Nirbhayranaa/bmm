package com.nirbhay.bmm.businessserver.dao;

import com.nirbhay.bmm.model.bs.Movie;
import com.nirbhay.bmm.model.bs.Screen;

import java.util.List;
import java.util.Optional;

public interface ScreenDao {
	List<Screen> fetchAllScreensByTheatreId(long theatreId);

    Optional<Screen> findById(long screenId);
}