package com.nirbhay.bmm.businessserver.dao.impl;

import com.nirbhay.bmm.businessserver.dao.OfferDao;
import com.nirbhay.bmm.businessserver.repository.OfferRepository;
import com.nirbhay.bmm.model.bs.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class OfferDaoImpl implements OfferDao {

    private final OfferRepository repository;

    public OfferDaoImpl(OfferRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<Offer> saveAll(List<Offer> offers) {
        repository.saveAll(offers);
        return offers;
    }

    @Override
    public List<Offer> findAll() {
        return repository.findAll();
    }
}
