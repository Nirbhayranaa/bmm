package com.nirbhay.bmm.businessserver.dao.impl;

import com.nirbhay.bmm.businessserver.dao.AddressDao;
import com.nirbhay.bmm.businessserver.repository.AddressRepository;
import com.nirbhay.bmm.model.bs.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class AddressDaoImpl implements AddressDao {
	private final AddressRepository repository;

    public AddressDaoImpl(AddressRepository repository) {
        this.repository = repository;
    }


    @Override
	public Address saveAddress(Address address) {
		log.info("Saving address to db {}", address);
		return repository.save(address);
	}
}