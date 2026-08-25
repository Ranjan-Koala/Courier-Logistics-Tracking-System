package com.courier.tracking.system.controller;

import java.util.List;
import java.util.Map;

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

import com.courier.tracking.system.requestdto.DeliveryAgentRequestDto;
import com.courier.tracking.system.responsedto.DeliveryAgentResponseDto;
import com.courier.tracking.system.service.DeliveryAgentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deliveryAgents")
public class DeliveryAgentController {

	private final DeliveryAgentService deliveryAgentService;

	@PostMapping
	public ResponseEntity<DeliveryAgentResponseDto> createAgent(
			@Valid @RequestBody DeliveryAgentRequestDto deliveryAgentRequestDto) {

		DeliveryAgentResponseDto response = deliveryAgentService.createAgentResponseDto(deliveryAgentRequestDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<List<DeliveryAgentResponseDto>> getAllDeliveryAgent() {

		List<DeliveryAgentResponseDto> response = deliveryAgentService.getAllAgent();

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{deliveryAgentId}")
	public ResponseEntity<DeliveryAgentResponseDto> getById(@PathVariable Long deliveryAgentId) {

		DeliveryAgentResponseDto response = deliveryAgentService.getById(deliveryAgentId);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/vehicle/{vehicleNumber}")
	public ResponseEntity<DeliveryAgentResponseDto> getByVehicleNumber(@PathVariable String vehicleNumber) {

		DeliveryAgentResponseDto response = deliveryAgentService.getByVehichleNumber(vehicleNumber);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/contact/{deliveryPhone}")
	public ResponseEntity<DeliveryAgentResponseDto> getByContact(@PathVariable String deliveryPhone) {

		DeliveryAgentResponseDto response = deliveryAgentService.getByContact(deliveryPhone);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/rating")
	public ResponseEntity<List<DeliveryAgentResponseDto>> getByMinimumRating(@RequestParam Double rating) {

		List<DeliveryAgentResponseDto> response = deliveryAgentService.getByMinimumRating(rating);

		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{deliveryId}")
	public ResponseEntity<DeliveryAgentResponseDto> updateAgent(@PathVariable Long deliveryId,
			@RequestBody Map<String, Object> updates) {

		DeliveryAgentResponseDto response = deliveryAgentService.updateAgentResponseDto(deliveryId, updates);

		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{deliveryId}/availability")
	public ResponseEntity<DeliveryAgentResponseDto> updateAgentAvailability(@PathVariable Long deliveryId,
			@RequestBody Map<String, Object> updates) {

		DeliveryAgentResponseDto response = deliveryAgentService.updateAgentAvailablity(deliveryId, updates);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{deliveryId}")
	public ResponseEntity<Void> deleteDeliveryAgent(@PathVariable Long deliveryId) {

		deliveryAgentService.deleteDeliveryAgent(deliveryId);

		return ResponseEntity.noContent().build();
	}
}