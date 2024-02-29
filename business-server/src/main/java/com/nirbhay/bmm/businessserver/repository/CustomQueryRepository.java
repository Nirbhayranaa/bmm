package com.nirbhay.bmm.businessserver.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nirbhay Rana
 */
@Repository
public class CustomQueryRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public List<?> findAllTheatreBySearchCriteria(String query) {
        return entityManager.createNativeQuery(query).getResultList();
    }

}

