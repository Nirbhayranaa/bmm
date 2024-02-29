package com.nirbhay.bmm.businessserver.dao.impl;

import com.nirbhay.bmm.businessserver.dao.TheatreDao;
import com.nirbhay.bmm.businessserver.repository.CustomQueryRepository;
import com.nirbhay.bmm.businessserver.repository.ScreenRepository;
import com.nirbhay.bmm.businessserver.repository.TheaterRepository;
import com.nirbhay.bmm.model.bs.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.nirbhay.bmm.utils.TimeUtil.convertToLocalDate;
import static com.nirbhay.bmm.utils.TimeUtil.convertToLocalTime;

/**
 * @author Nirbhay Rana
 */

@Component
@Slf4j
public class TheaterDaoImpl implements TheatreDao {
    private final TheaterRepository repository;
    private final ScreenRepository screenRepository;
    private final CustomQueryRepository customQueryRepository;

    public TheaterDaoImpl(TheaterRepository repository, ScreenRepository screenRepository, CustomQueryRepository customQueryRepository) {
        this.repository = repository;
        this.screenRepository = screenRepository;
        this.customQueryRepository = customQueryRepository;
    }

    @Override
    public List<Object> fetchAllTheatreBySearchCriteria(SearchCriteria searchCriteria) {
        log.info("Finding theater");
        LocalDate showDate = searchCriteria.getShowDate();
        Date sqlDate = showDate != null ? Date.valueOf(showDate) : null;

        LocalTime showStartTiming = searchCriteria.getShowStartTiming();
        Time sqlTime = showStartTiming != null ? Time.valueOf(showStartTiming) : null;
        return repository.findAllTheatreBySearchCriteria(
                sqlDate,
                sqlTime,
                searchCriteria.getCity(),
                searchCriteria.getMovieName());
    }

    @Override
    public Theater createTheater(Theater theater) {
        log.info("Adding new theater to db");
        return repository.save(theater);
    }

    @Override
    public List<City> findByCityName() {
        //todo: implement it
        return null;
    }

    @Override
    public Optional<Theater> findById(long theaterId) {
        return repository.findById(theaterId);
    }

    @Override
    public List<TheaterResponse> fetchAllTheatreBySearchCriteria(String query) {
        List<TheaterResponse> responseList = new ArrayList<>();

        List<?> resultList = customQueryRepository.findAllTheatreBySearchCriteria(query);
        for (Object result : resultList) {
            Object[] resultArray = (Object[]) result;
            TheaterResponse theaterResponse = mapToTheaterResponse(resultArray);
            responseList.add(theaterResponse);
        }

        return responseList;
    }

    private TheaterResponse mapToTheaterResponse(Object[] resultArray) {
        return  TheaterResponse.builder()
                .theaterId((long) resultArray[0])
                .theaterName((String) resultArray[1])
                .addressLines((String) resultArray[2])
                .primaryPhone((String) resultArray[3])
                .cityName((String) resultArray[4])
                .stateName((String) resultArray[5])
                .movieShowId((long) resultArray[6])
                .showTimingFrom(convertToLocalTime((Time) resultArray[7]))
                .showTimingTo(convertToLocalTime((Time) resultArray[8]))
                .showDate(convertToLocalDate((Date) resultArray[9]))
                .screenId((long) resultArray[10])
                .screenName((String) resultArray[11])
                .movieId((long) resultArray[12])
                .movieName((String) resultArray[13])
                .build();
    }

    @Override
    public Screen addScreen(Screen screen) {
        return screenRepository.save(screen);
    }


}
