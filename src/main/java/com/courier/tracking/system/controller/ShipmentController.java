package com.courier.tracking.system.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.courier.tracking.system.requestdto.PackageRequestDto;
import com.courier.tracking.system.requestdto.PaymentRequestDto;
import com.courier.tracking.system.requestdto.ShipmentRequestDto;
import com.courier.tracking.system.requestdto.TrackingHistoryRequestDto;
import com.courier.tracking.system.responsedto.ShipmentResponseDto;
import com.courier.tracking.system.service.ShipmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shipments")
public class ShipmentController {

	private final ShipmentService shipmentService;

	@PostMapping
	public ResponseEntity<ShipmentResponseDto> createShipment(@Valid @RequestPart ShipmentRequestDto shipmentRequestDto,
			@RequestPart PackageRequestDto packageRequestDto, @RequestPart PaymentRequestDto paymentRequestDto,
			@RequestPart TrackingHistoryRequestDto trackingHistoryRequestDto) {
		ShipmentResponseDto response = shipmentService.createShipment(shipmentRequestDto, packageRequestDto,
				paymentRequestDto, trackingHistoryRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<List<ShipmentResponseDto>> getAllShipments() {
		List<ShipmentResponseDto> response = shipmentService.getAllShipment();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{shipmentId}")
	public ResponseEntity<ShipmentResponseDto> getById(@PathVariable Long shipmentId) {
		ShipmentResponseDto response = shipmentService.getById(shipmentId);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/tracking/{trackingNumber}")
	public ResponseEntity<ShipmentResponseDto> getByTrackingNumber(@PathVariable String trackingNumber) {
		ShipmentResponseDto response = shipmentService.getByTrackingNumber(trackingNumber);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{shipmentId}/status")
	public ResponseEntity<ShipmentResponseDto> updateShipmentStatus(@PathVariable Long shipmentId,
			@PathVariable Map<String, Object> update) {
		ShipmentResponseDto response = shipmentService.updateShipmentStatus(shipmentId, update);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{shipmentId}/delivery-agent/{agentId}")
	public ResponseEntity<ShipmentResponseDto> assignDeliveryAgent(@PathVariable Long shipmentId,
			@PathVariable Long assignId) {
		ShipmentResponseDto response = shipmentService.assignDeliveryAgent(shipmentId, assignId);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{shipmentId}/warehouse/{warehouseId}")
	public ResponseEntity<ShipmentResponseDto> assignWarehouse(@PathVariable Long shipmentId,
			@PathVariable Long warehouseId) {

		ShipmentResponseDto response = shipmentService.assignWarehouse(shipmentId, warehouseId);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{shipmentId}")
	public ResponseEntity<Void> deleteShipment(@PathVariable Long shipmentId) {

		shipmentService.deleteShipment(shipmentId);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<ShipmentResponseDto>> getByCustomer(@PathVariable Long customerId) {

		List<ShipmentResponseDto> response = shipmentService.getByCustomer(customerId);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/warehouse/{warehouseId}")
	public ResponseEntity<List<ShipmentResponseDto>> getByWarehouse(@PathVariable Long warehouseId) {
		List<ShipmentResponseDto> response = shipmentService.getByCustomer(warehouseId);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/delivery-agent/{deliveryAgentId}")
	public ResponseEntity<List<ShipmentResponseDto>> getByDeliveryAgent(@PathVariable Long deliveryAgentId) {

		List<ShipmentResponseDto> response = shipmentService.getByDeliveryAgent(deliveryAgentId);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/route")
	public ResponseEntity<List<ShipmentResponseDto>> getBySourceAndDestination(

			@RequestParam String source,

			@RequestParam String destination) {

		List<ShipmentResponseDto> response = shipmentService.getBySourceAndDestination(source, destination);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/delivery-date")
	public ResponseEntity<List<ShipmentResponseDto>> getByDeliveryDate(

			@RequestParam LocalDateTime deliveryDate) {

		List<ShipmentResponseDto> response = shipmentService.getByDeliveryDate(deliveryDate);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ShipmentResponseDto>> getByPaginationAndSorting(

			@RequestParam(defaultValue = "0") int pageNumber,

			@RequestParam(defaultValue = "10") int pageSize) {

		Page<ShipmentResponseDto> response = shipmentService.getByPaginationAndSorting(pageNumber, pageSize);

		return ResponseEntity.ok(response);
	}

}
