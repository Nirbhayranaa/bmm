package com.nirbhay.bmm.businessserver.repository;

import com.nirbhay.bmm.model.bs.SearchCriteria;
import com.nirbhay.bmm.model.bs.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * @author Nirbhay Rana
 */
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    @Query(value = "SELECT t.theater_name theatreName, " +
            "a.address_lines addressLines, "+
            "a.primary_phone primaryPhone, "+
            "c.city_name cityName, "+
            "c.state_name stateName, "+
            "ms.show_timing_from showTimingFrom, " +
            "ms.show_timing_to showTimingTo, " +
            "ms.show_date showDate, " +
            "scr.screen_name screenName, "+
            "mv.movie_name movieName "+
            "FROM theater t " +
            "LEFT JOIN address a ON a.address_id = t.address_id " +
            "LEFT JOIN city c ON a.city_id = c.city_id " +
            "INNER JOIN screen scr ON scr.theater_id = t.theater_id AND scr.is_deleted = 0 " +
            "INNER JOIN movie_show ms ON ms.screen_id = scr.screen_id AND scr.is_deleted = 0 " +
            "INNER JOIN movie mv ON mv.movie_id = ms.movie_id AND mv.is_deleted = 0 " +
            "WHERE t.is_deleted = 0 " +
            "AND (CAST(:showDate AS DATE) IS NULL OR ms.show_date = CAST(:showDate AS DATE)) " +
            "AND (CAST(:showStartTiming AS TIME) IS NULL OR ms.show_timing_from = CAST(:showStartTiming AS TIME)) " +
            "AND (:city IS NULL OR c.city_name = :city) " +
            "AND (:movieName IS NULL OR mv.movie_name = :movieName) " +
            "AND ms.show_date >= CURRENT_DATE ",
            nativeQuery = true)
    List<Object> findAllTheatreBySearchCriteria(
            @Param("showDate") Date showDate,
                                                @Param("showStartTiming") Time showStartTiming,
                                                @Param("city") String city,
                                                @Param("movieName") String movieName);

    @Query(value = "?1", nativeQuery = true)
    List<Object> findAllTheatreBySearchCriteria(String query);
}
