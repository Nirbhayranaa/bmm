package com.nirbhay.bmm.businessserver.repository;

import com.nirbhay.bmm.model.bs.City;
import com.nirbhay.bmm.model.bs.SeatCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nirbhay Rana
 */
@Repository
public interface SeatCategoryRepository extends JpaRepository<SeatCategory, Long> {
}
