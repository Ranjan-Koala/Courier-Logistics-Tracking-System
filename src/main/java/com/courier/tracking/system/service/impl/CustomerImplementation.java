package com.courier.tracking.system.service.impl;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.courier.tracking.system.entity.Customer;
import com.courier.tracking.system.repository.CustomerRepository;
import com.courier.tracking.system.requestdto.CustomerRequestDto;
import com.courier.tracking.system.responsedto.CustomerResponseDto;
import com.courier.tracking.system.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerImplementation implements CustomerService {

	private final CustomerRepository customerRepository;
	private final ModelMapper modelMapper;

	@Override
	public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
		// Manual Adding
//		Customer saved = new Customer();
//		saved.setName(customerRequestDto.getName());
//		saved.setEmail(customerRequestDto.getEmail());
//		
//		Customer sa = customerRepository.save(saved);
//		CustomerResponseDto sav = new CustomerResponseDto();
//		sav.setId(sa.getCustomerId());
//		sav.setName(sa.getName());

		// We can also use like create object of customer or directly use in save method
		Customer customer = modelMapper.map(customerRequestDto, Customer.class);
		Customer savedCustomer = customerRepository.save(customer);
		return modelMapper.map(savedCustomer, CustomerResponseDto.class);
	}

	@Override
	public List<CustomerResponseDto> getAllCustomer() {
		List<Customer> customers = customerRepository.findAll();

		return customers.stream().map(customer -> modelMapper.map(customer, CustomerResponseDto.class)).toList();
	}

	@Override
	public CustomerResponseDto getById(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + customerId));
		return modelMapper.map(customer, CustomerResponseDto.class);
	}

	@Override
	public CustomerResponseDto getByEmail(String customerEmail) {
		Customer customer = customerRepository.findByEmail(customerEmail)
				.orElseThrow(() -> new IllegalArgumentException("Customer not found with Email: " + customerEmail));
		return modelMapper.map(customer, CustomerResponseDto.class);
	}

	@Override
	public CustomerResponseDto getByContact(String customerPhoneNumber) {
		Customer customer = customerRepository.findByPhoneNumber(customerPhoneNumber)
				.orElseThrow(() -> new IllegalArgumentException("Customer not found with Contact: " + customerPhoneNumber));
		return modelMapper.map(customer, CustomerResponseDto.class);
	}

	@Override
	public CustomerResponseDto updateCustomer(Long customerId, Map<String, Object> updates) {

		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + customerId));

		updates.forEach((field, value) -> {

			switch (field) {
			case "name" -> customer.setName((String) value);
			case "email" -> customer.setEmail((String) value);
			case "contact" -> customer.setPhoneNumber((String) value);
			default -> throw new IllegalArgumentException("Invalid field: " + field);
			}
		});

		Customer updatedCustomer = customerRepository.save(customer);

		return modelMapper.map(updatedCustomer, CustomerResponseDto.class);
	}

	@Override
	public void deleteCustomer(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + customerId));
		customerRepository.deleteById(customerId);

	}

	@Override
	public Page<CustomerResponseDto> getCustomers(int pageNumber, int pageSize, String sortBy, String sortDirection) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize,
				Sort.by(Sort.Direction.fromString(sortDirection), sortBy));

		Page<Customer> customerPage = customerRepository.findAll(pageable);

		return customerPage.map(customer -> modelMapper.map(customer, CustomerResponseDto.class));
	}

}
