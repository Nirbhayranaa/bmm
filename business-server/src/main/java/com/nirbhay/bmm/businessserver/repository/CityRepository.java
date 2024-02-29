package com.nirbhay.bmm.businessserver.repository;

import com.nirbhay.bmm.model.bs.Address;
import com.nirbhay.bmm.model.bs.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nirbhay Rana
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    long deleteByCityName(String cityName);

    Optional<City> findByCityName(String cityName);
}
