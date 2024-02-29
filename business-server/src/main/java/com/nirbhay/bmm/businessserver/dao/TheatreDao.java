package com.nirbhay.bmm.businessserver.dao;

import com.nirbhay.bmm.model.bs.*;

import java.util.List;
import java.util.Optional;

public interface TheatreDao {

	List<Object> fetchAllTheatreBySearchCriteria(SearchCriteria searchCriteria);

	Theater createTheater(Theater theater);

	List<City> findByCityName();

	Screen addScreen(Screen screen);

	Optional<Theater> findById(long theaterId);

	List<TheaterResponse> fetchAllTheatreBySearchCriteria(String query);
}
