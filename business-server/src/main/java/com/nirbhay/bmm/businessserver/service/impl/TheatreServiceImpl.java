package com.nirbhay.bmm.businessserver.service.impl;

import java.util.List;
import java.util.Optional;

import com.nirbhay.bmm.businessserver.dao.AddressDao;
import com.nirbhay.bmm.businessserver.dao.CityDao;
import com.nirbhay.bmm.businessserver.dao.TheatreDao;
import com.nirbhay.bmm.businessserver.repository.AddressRepository;
import com.nirbhay.bmm.businessserver.service.TheatreService;
import com.nirbhay.bmm.businessserver.service.strategy.search.impl.TheaterSearchContext;
import com.nirbhay.bmm.model.bs.*;
import com.nirbhay.bmm.model.exception.CityNotFoundException;
import com.nirbhay.bmm.model.exception.TheaterAlreadyExistsException;
import com.nirbhay.bmm.model.exception.TheaterNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class TheatreServiceImpl implements TheatreService {
	@Autowired
	private AddressRepository addressRepository;


	@Autowired
	private TheatreDao theatreDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private CityDao cityDao;

	@Autowired
	private TheaterSearchContext theaterSearchContext;

	@Override
	@Transactional
	public Theater createTheater(Theater theater) {
		log.info("Creating a theater  {}", theater);
		City city = getCity(theater);
		Address addressToSave = theater.getAddress();
		addressToSave.setCity(city);
		Address address = addressRepository.save(addressToSave);
		theater.setAddress(address);
		return saveTheater(theater);

	}

	private Theater saveTheater(Theater theater) {
		log.info("Saving theater");
		try {
			return theatreDao.createTheater(theater);
		}catch (DataIntegrityViolationException e) {
			if (e.getCause().getCause() instanceof PSQLException psqlException) {
                if ("23505".equals(psqlException.getSQLState())) {
					// This is a unique constraint violation
					throw new TheaterAlreadyExistsException("Theater with name " + theater.getTheaterName() + " already exists.");
				}
			}
			throw e;
			}
	}

	private City getCity(Theater theater) {
		String cityName = theater.getAddress().getCity().getCityName();
		City city = cityDao.getCityByName(cityName);
		if(city ==null){
			log.error("Invalid city or city is not onboarded yet");
			throw new CityNotFoundException(cityName);
		}
		return city;
	}

	@Override
	public List<TheaterResponse> fetchAllTheatreBySearchCriteria(SearchCriteria searchCriteria) {
		log.info("Fetching theaters by search criteria {}", searchCriteria);
		String query = theaterSearchContext.buildQuery(searchCriteria);
		return theatreDao.fetchAllTheatreBySearchCriteria(query);
	}

	@Override
	public Screen addScreen(ScreenAddRequest screenAddRequest) {
		Theater theater = getTheater(screenAddRequest);
		Screen screen = Screen.builder()
				.theater(theater)
				.screenName(screenAddRequest.getScreenName())
				.build();
		return theatreDao.addScreen(screen);
	}

	private Theater getTheater(ScreenAddRequest screenAddRequest) {
		Optional<Theater> theaterOptional = theatreDao.findById(screenAddRequest.getTheaterId());
		return theaterOptional.orElseThrow(() -> new TheaterNotFoundException("Theater not found with id " + screenAddRequest.getTheaterId()));
	}

}
