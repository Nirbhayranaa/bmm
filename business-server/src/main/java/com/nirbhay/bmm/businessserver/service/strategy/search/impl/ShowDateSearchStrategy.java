package com.nirbhay.bmm.businessserver.service.strategy.search.impl;

import com.nirbhay.bmm.businessserver.service.strategy.search.TheaterSearchStrategy;
import com.nirbhay.bmm.model.bs.SearchCriteria;
import org.springframework.stereotype.Component;

/**
 * @author Nirbhay Rana
 */
@Component
public class ShowDateSearchStrategy implements TheaterSearchStrategy {

    @Override
    public String apply(SearchCriteria criteria) {
        if (criteria.getShowDate() != null) {
            return " AND ms.show_date = CAST('"+ criteria.getShowDate()+"' AS DATE)";
        } else {
            return "";
        }
    }
}
