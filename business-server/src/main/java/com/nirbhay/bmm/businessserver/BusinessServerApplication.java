package com.nirbhay.bmm.businessserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.nirbhay.bmm.model.bs", "com.nirbhay.bmm.model.usermanagement"})
public class BusinessServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessServerApplication.class, args);
	}

}
