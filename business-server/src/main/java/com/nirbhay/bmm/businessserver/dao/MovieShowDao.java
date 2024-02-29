package com.nirbhay.bmm.businessserver.dao;

import com.nirbhay.bmm.model.bs.MovieShow;

import java.util.List;
import java.util.Optional;

public interface MovieShowDao{

	List<MovieShow> insertMovieShow(List<MovieShow> movieShow);
	
	MovieShow updateMovieShow(MovieShow movieShow);

	List<MovieShow> fetchAllShowsByTheatreId(long theatreId);

	void logicalDelete(long movieShowid);

	Optional<MovieShow> findById(long movieShowId);


}