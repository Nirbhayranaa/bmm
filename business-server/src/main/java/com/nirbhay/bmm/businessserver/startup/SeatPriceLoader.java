package com.nirbhay.bmm.businessserver.startup;

import com.nirbhay.bmm.businessserver.dao.SeatPriceDao;
import com.nirbhay.bmm.model.bs.SeatPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Component
@Slf4j
public class SeatPriceLoader implements ApplicationRunner {

    @Autowired
    private SeatPriceDao seatPriceDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Loading cache for seat prices");
        List<SeatPrice> allSeatPrices = seatPriceDao.findAll();
        allSeatPrices.forEach(sp -> seatPriceDao.saveCache(sp, sp.getSeatCategory().getSeatCategoryId()));
        log.info("Seat prices cache load completed");
    }
}
