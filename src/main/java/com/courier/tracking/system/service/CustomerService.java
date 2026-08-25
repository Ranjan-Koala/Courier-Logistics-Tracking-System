package com.courier.tracking.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.courier.tracking.system.requestdto.CustomerRequestDto;
import com.courier.tracking.system.responsedto.CustomerResponseDto;

public interface CustomerService {
	CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

	List<CustomerResponseDto> getAllCustomer();

	CustomerResponseDto getById(Long customerId);

	CustomerResponseDto getByEmail(String customerEmail);

	CustomerResponseDto getByContact(String customerPhoneNumber);

	CustomerResponseDto updateCustomer(Long customerId, Map<String, Object> updates);

	void deleteCustomer(Long customerId);

	Page<CustomerResponseDto> getCustomers(int pageNumber, int pageSize, String sortBy, String sortDirection);

}
