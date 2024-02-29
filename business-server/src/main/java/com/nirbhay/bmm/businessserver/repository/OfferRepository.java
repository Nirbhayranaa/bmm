package com.nirbhay.bmm.businessserver.repository;

import com.nirbhay.bmm.model.bs.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
}
