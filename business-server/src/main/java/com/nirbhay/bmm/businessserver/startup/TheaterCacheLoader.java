package com.nirbhay.bmm.businessserver.startup;

import com.nirbhay.bmm.businessserver.dao.CityDao;
import com.nirbhay.bmm.businessserver.dao.TheatreDao;
import com.nirbhay.bmm.model.bs.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TheaterCacheLoader implements ApplicationRunner {

    @Autowired
    private TheatreDao theaterDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Loading theater cache");

        log.info("Loading complete for theater cache");
    }
}
