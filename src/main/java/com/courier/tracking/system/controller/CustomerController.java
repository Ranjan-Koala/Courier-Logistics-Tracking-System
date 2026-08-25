package com.courier.tracking.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.courier.tracking.system.requestdto.CustomerRequestDto;
import com.courier.tracking.system.responsedto.CustomerResponseDto;
import com.courier.tracking.system.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
	
	private final CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){
		CustomerResponseDto response = customerService.createCustomer(customerRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerResponseDto>> getAllCustomers(){
		List<CustomerResponseDto> response = customerService.getAllCustomer();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerResponseDto> getById(@PathVariable Long customerId){
		CustomerResponseDto response = customerService.getById(customerId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<CustomerResponseDto> getByEmail(@PathVariable String email){
		CustomerResponseDto response = customerService.getByEmail(email);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/phone/{phoneNumber}")
	public ResponseEntity<CustomerResponseDto> getByContact(@PathVariable String phoneNumber){
		CustomerResponseDto response = customerService.getByContact(phoneNumber);
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping("/{customerId}")
	public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Long customerId, @RequestBody Map<String, Object> updates){
		CustomerResponseDto response = customerService.updateCustomer(customerId, updates);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping
	public ResponseEntity<CustomerResponseDto> deleteCustomer(@PathVariable Long customerId){
		customerService.deleteCustomer(customerId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{page}")
	public ResponseEntity<Page<CustomerResponseDto>> getByPaginationAndSorting( @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "customerId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection){
		Page<CustomerResponseDto> response = customerService.getCustomers(pageNumber, pageSize, sortBy, sortDirection);
		return ResponseEntity.ok(response);
		
	}
	
	

}
