package com.nirbhay.bmm.businessserver.repository;

import com.nirbhay.bmm.model.bs.SeatPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatPriceRepository extends JpaRepository<SeatPrice, Long> {
    SeatPrice findBySeatCategory_SeatCategoryId(Long seatCategoryId);
}
