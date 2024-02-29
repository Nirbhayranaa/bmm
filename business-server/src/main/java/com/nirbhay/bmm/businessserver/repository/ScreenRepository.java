package com.nirbhay.bmm.businessserver.repository;

import com.nirbhay.bmm.model.bs.Movie;
import com.nirbhay.bmm.model.bs.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Nirbhay Rana
 */
@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
    @Query("select s from Screen s where s.theater.id = ?1")
    List<Screen> fetchAllScreensByTheatreId(Long id);
}
