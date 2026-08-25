package com.courier.tracking.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courier.tracking.system.responsedto.PaymentResponseDto;
import com.courier.tracking.system.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

	private final PaymentService paymentService;

	@GetMapping
	public ResponseEntity<List<PaymentResponseDto>> getAllPayment() {

		List<PaymentResponseDto> response = paymentService.getAllPayment();

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{paymentId}")
	public ResponseEntity<PaymentResponseDto> getById(@PathVariable Long paymentId) {

		PaymentResponseDto response = paymentService.getById(paymentId);

		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{paymentId}/status")
	public ResponseEntity<PaymentResponseDto> updateStatus(@PathVariable Long paymentId,
			@RequestBody Map<String, Object> updates) {

		PaymentResponseDto response = paymentService.updateStatus(paymentId, updates);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{paymentId}")
	public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId) {

		paymentService.deletePayment(paymentId);

		return ResponseEntity.noContent().build();
	}
}