package com.nirbhay.bmm.businessserver.controller;

import java.util.List;

import com.nirbhay.bmm.businessserver.controller.api.TheaterApi;
import com.nirbhay.bmm.businessserver.service.TheatreService;
import com.nirbhay.bmm.model.bs.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TheatreEndPoint implements TheaterApi {
	

	private final TheatreService theatreService;

    public TheatreEndPoint(TheatreService theatreService) {
        this.theatreService = theatreService;
    }


    @Override
	public ResponseEntity<Theater> createTheater(Theater theater) throws Exception {
		Theater t = theatreService.createTheater(theater);
		return ResponseEntity.ok(t);
	}


	@Override
	public ResponseEntity<List<TheaterResponse>> fetchAllTheatreBySearchCriteria(SearchCriteria searchCriteria) {
		List<TheaterResponse> li = theatreService.fetchAllTheatreBySearchCriteria(searchCriteria);
		return ResponseEntity.ok(li);
	}

	@Override
	public ResponseEntity<Screen> addScreen(ScreenAddRequest screenAddRequest) {
		Screen screen = theatreService.addScreen(screenAddRequest);
		return ResponseEntity.ok(screen);
	}
}