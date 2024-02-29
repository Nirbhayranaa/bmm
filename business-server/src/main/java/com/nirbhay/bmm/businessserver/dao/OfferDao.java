package com.nirbhay.bmm.businessserver.dao;

import com.nirbhay.bmm.model.bs.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferDao {
    List<Offer> saveAll(List<Offer> offers);
    List<Offer> findAll();
}
