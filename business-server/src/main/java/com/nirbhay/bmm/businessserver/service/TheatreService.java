package com.nirbhay.bmm.businessserver.service;

import com.nirbhay.bmm.model.bs.*;

import java.util.List;

public interface TheatreService {

	Theater createTheater(Theater theater);
	List<TheaterResponse> fetchAllTheatreBySearchCriteria(SearchCriteria searchCriteria);

    Screen addScreen(ScreenAddRequest screenAddRequest);
}
