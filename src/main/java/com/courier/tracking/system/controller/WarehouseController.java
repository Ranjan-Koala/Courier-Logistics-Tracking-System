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

import com.courier.tracking.system.requestdto.WarehouseRequestDto;
import com.courier.tracking.system.responsedto.WarehouseResponseDto;
import com.courier.tracking.system.service.WarehouseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/warehouses")
public class WarehouseController {

	private final WarehouseService warehouseService;

	@PostMapping
	public ResponseEntity<WarehouseResponseDto> createWarehouse(
			@Valid @RequestBody WarehouseRequestDto warehouseRequestDto) {

		WarehouseResponseDto response = warehouseService.createWarehouse(warehouseRequestDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<List<WarehouseResponseDto>> getAllWarehouse() {

		List<WarehouseResponseDto> response = warehouseService.getAllWarehouse();

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{warehouseId}")
	public ResponseEntity<WarehouseResponseDto> getById(@PathVariable Long warehouseId) {

		WarehouseResponseDto response = warehouseService.getById(warehouseId);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/capacity")
	public ResponseEntity<List<WarehouseResponseDto>> getByCapacity(@RequestParam Integer capacity) {

		List<WarehouseResponseDto> response = warehouseService.getByCapacity(capacity);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/minimum-capacity")
	public ResponseEntity<List<WarehouseResponseDto>> getByMinimumCapacity(@RequestParam Integer capacity) {

		List<WarehouseResponseDto> response = warehouseService.getByMinimumCapacity(capacity);

		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{warehouseId}")
	public ResponseEntity<WarehouseResponseDto> updateWarehouse(@PathVariable Long warehouseId,
			@RequestBody Map<String, Object> updates) {

		WarehouseResponseDto response = warehouseService.updateWarehouse(warehouseId, updates);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{warehouseId}")
	public ResponseEntity<Void> deleteWarehouse(@PathVariable Long warehouseId) {

		warehouseService.deleteWarehouse(warehouseId);

		return ResponseEntity.noContent().build();
	}
}