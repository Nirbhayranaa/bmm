package com.nirbhay.bmm.businessserver.service.strategy.search.impl;

import com.nirbhay.bmm.businessserver.service.strategy.search.TheaterSearchStrategy;
import com.nirbhay.bmm.model.bs.SearchCriteria;
import org.springframework.stereotype.Component;

/**
 * @author Nirbhay Rana
 */
@Component
public class CitySearchStrategy implements TheaterSearchStrategy {

    @Override
    public String apply(SearchCriteria criteria) {
        if (criteria.getCity() != null) {
            return " AND c.city_name = '"+ criteria.getCity()+"'";
        } else {
            return "";
        }
    }
}
