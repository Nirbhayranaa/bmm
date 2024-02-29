package com.nirbhay.bmm.businessserver.service.strategy.search.impl;

import com.nirbhay.bmm.businessserver.service.strategy.search.TheaterSearchStrategy;
import com.nirbhay.bmm.model.bs.SearchCriteria;
import org.springframework.stereotype.Component;

/**
 * @author Nirbhay Rana
 */
@Component
public class MovieSearchStrategy implements TheaterSearchStrategy {

    @Override
    public String apply(SearchCriteria criteria) {
        if (criteria.getMovieName() != null) {
            return " AND mv.movie_name = '"+ criteria.getMovieName()+"'";
        } else {
            return "";
        }
    }
}
