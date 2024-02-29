package com.nirbhay.bmm.businessserver.dao.impl;

import com.nirbhay.bmm.businessserver.dao.CityDao;
import com.nirbhay.bmm.businessserver.repository.CityRepository;
import com.nirbhay.bmm.model.bs.City;
import com.nirbhay.bmm.model.exception.CityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.nirbhay.bmm.constants.CacheConstant.CITIES;


@Component
@Slf4j
public class CityDaoImpl implements CityDao {
    private final CityRepository repository;

    public CityDaoImpl(CityRepository repository) {
        this.repository = repository;
    }


    @Override
    @CachePut(cacheNames = CITIES, key = "#city.cityName")
    public City saveCity(City city) {
        log.info("Saving address to db {}", city);
        return repository.save(city);
    }

    @Override
    public List<City> findAll() {
       return repository.findAll();
    }

    @CacheEvict(cacheNames = CITIES, key = "#cityName")
    public void deleteByCityName(String cityName) {
        repository.deleteByCityName(cityName);
    }

    @Cacheable(cacheNames = CITIES, key = "#cityName")
    public City getCityByName(String cityName) {
        return repository.findByCityName(cityName).orElseThrow(() -> new CityNotFoundException(cityName));
    }
}