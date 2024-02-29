package com.nirbhay.bmm.businessserver.dao;

import com.nirbhay.bmm.model.bs.City;

import java.util.List;

public interface CityDao {
	City saveCity(City city);

    List<City> findAll();
    City getCityByName(String cityName);
}
