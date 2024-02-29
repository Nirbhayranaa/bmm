package com.nirbhay.bmm.businessserver.service.impl;

import com.nirbhay.bmm.businessserver.dao.ScreenDao;
import com.nirbhay.bmm.businessserver.service.ScreenService;
import com.nirbhay.bmm.model.bs.Screen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ScreenServiceImpl implements ScreenService {

    private final ScreenDao screenDao;

    public ScreenServiceImpl(ScreenDao screenDao) {
        this.screenDao = screenDao;
    }


    @Override
    public List<Screen> fetchAllScreensByTheatreId(long theatreId) {
        return screenDao.fetchAllScreensByTheatreId(theatreId);
    }
}
