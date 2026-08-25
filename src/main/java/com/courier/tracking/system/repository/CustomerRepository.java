package com.courier.tracking.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courier.tracking.system.entity.Customer;
import com.courier.tracking.system.responsedto.CustomerResponseDto;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	 Optional<Customer> findByEmail(String customerEmail);

	 Optional<Customer> findByPhoneNumber(String customerPhoneNumber);
	
}
