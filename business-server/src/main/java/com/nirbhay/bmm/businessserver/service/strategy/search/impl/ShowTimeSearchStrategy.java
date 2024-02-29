package com.nirbhay.bmm.businessserver.service.strategy.search.impl;

import com.nirbhay.bmm.businessserver.service.strategy.search.TheaterSearchStrategy;
import com.nirbhay.bmm.model.bs.SearchCriteria;
import org.springframework.stereotype.Component;

/**
 * @author Nirbhay Rana
 */
@Component
public class ShowTimeSearchStrategy implements TheaterSearchStrategy {

    @Override
    public String apply(SearchCriteria criteria) {
        if (criteria.getShowStartTiming() != null) {
            return " AND ms.show_timing_from = CAST('"+ criteria.getShowStartTiming()+"' AS TIME)";
        } else {
            return "";
        }
    }
}
