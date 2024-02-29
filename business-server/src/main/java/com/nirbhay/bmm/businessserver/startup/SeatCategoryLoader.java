package com.nirbhay.bmm.businessserver.startup;

import com.nirbhay.bmm.businessserver.dao.CityDao;
import com.nirbhay.bmm.businessserver.dao.SeatCategoryDao;
import com.nirbhay.bmm.model.bs.City;
import com.nirbhay.bmm.model.bs.SeatCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class SeatCategoryLoader implements ApplicationRunner {

    @Autowired
    private SeatCategoryDao dao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Loading cache for seat categories");
        List<SeatCategory> allSeatCategory = dao.findAll();
        allSeatCategory.forEach(sc -> dao.saveCache(sc, sc.getSeatCategoryId()));
        log.info("seat categories cache load completed");
    }
}
