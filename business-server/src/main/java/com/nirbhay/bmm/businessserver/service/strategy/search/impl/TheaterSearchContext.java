package com.nirbhay.bmm.businessserver.service.strategy.search.impl;

import com.nirbhay.bmm.businessserver.service.strategy.search.TheaterSearchStrategy;
import com.nirbhay.bmm.model.bs.SearchCriteria;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nirbhay Rana
 */
@Component
public class TheaterSearchContext {

    private static final String baseQuery = """
            SELECT t.theater_id theatreId,
            t.theater_name theatreName,
                        a.address_lines addressLines,
                        a.primary_phone primaryPhone,
                        c.city_name cityName,
                        c.state_name stateName,
                        ms.movie_show_id movieShowId,
                        ms.show_timing_from showTimingFrom,
                        ms.show_timing_to showTimingTo,
                        ms.show_date showDate,
                        scr.screen_id screenId,
                        scr.screen_name screenName,
                        mv.movie_id movieId,
                        mv.movie_name movieName
                        FROM theater t
                        LEFT JOIN address a ON a.address_id = t.address_id
                        LEFT JOIN city c ON a.city_id = c.city_id
                        INNER JOIN screen scr ON scr.theater_id = t.theater_id AND scr.is_deleted = 0
                        INNER JOIN movie_show ms ON ms.screen_id = scr.screen_id AND scr.is_deleted = 0
                        INNER JOIN movie mv ON mv.movie_id = ms.movie_id AND mv.is_deleted = 0
                        WHERE t.is_deleted = 0
                        """;

    private final List<TheaterSearchStrategy> strategies;

    public TheaterSearchContext(List<TheaterSearchStrategy> strategies) {
        this.strategies = strategies;
    }

    public String buildQuery(SearchCriteria criteria) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(baseQuery);


        for (TheaterSearchStrategy strategy : strategies) {
            String condition = strategy.apply(criteria);
            if (!condition.isEmpty()) {
                queryBuilder.append(condition);
            }
        }
        return queryBuilder.toString();
    }
}
