package com.nirbhay.bmm.businessserver.service;

import com.nirbhay.bmm.model.bs.MovieShow;
import com.nirbhay.bmm.model.bs.MovieShowRequest;

import java.util.List;

public interface MovieShowService {

    List<MovieShow> saveMovieShow(MovieShowRequest movieShow);


    List<MovieShow> fetchAllShowsByTheatreId(long theatreId);

    void deleteMovieShow(Long movieShow);

}
