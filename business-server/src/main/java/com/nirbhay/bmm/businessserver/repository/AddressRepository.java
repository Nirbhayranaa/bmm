package com.nirbhay.bmm.businessserver.repository;

import com.nirbhay.bmm.model.bs.Address;
import com.nirbhay.bmm.model.bs.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nirbhay Rana
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
