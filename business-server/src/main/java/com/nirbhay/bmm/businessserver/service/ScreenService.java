package com.nirbhay.bmm.businessserver.service;

import com.nirbhay.bmm.model.bs.Movie;
import com.nirbhay.bmm.model.bs.Screen;

import java.util.List;

public interface ScreenService {

	List<Screen> fetchAllScreensByTheatreId(long theatreId);


}
