package com.courier.tracking.system.service.impl;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.courier.tracking.system.entity.Payment;
import com.courier.tracking.system.entity.PaymentStatus;
import com.courier.tracking.system.repository.PaymentRepository;
import com.courier.tracking.system.responsedto.PaymentResponseDto;
import com.courier.tracking.system.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentImplementation implements PaymentService {

	private final PaymentRepository paymentRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<PaymentResponseDto> getAllPayment() {
		List<Payment> payments = paymentRepository.findAll();
		if (payments.isEmpty()) {
			throw new IllegalArgumentException("No Payment found" + payments);
		}
		return payments.stream().map(aList -> modelMapper.map(aList, PaymentResponseDto.class)).toList();
	}

	@Override
	public PaymentResponseDto getById(Long paymentId) {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new IllegalArgumentException("No Payment found with Id" + paymentId));
		return modelMapper.map(payment, PaymentResponseDto.class);
	}

	@Override
	public PaymentResponseDto updateStatus(Long paymentId, Map<String, Object> updates) {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new IllegalArgumentException("No Payment found with Id" + paymentId));
		updates.forEach((field, value) -> {
			switch (field) {

			case "paymentStatus" -> {

				PaymentStatus status = PaymentStatus.valueOf(value.toString().toUpperCase());

				payment.setPaymentStatus(status);
			}

			default -> throw new IllegalArgumentException("Invalid field: " + field);
			}
		});

		Payment updatedPayment = paymentRepository.save(payment);

		return modelMapper.map(updatedPayment, PaymentResponseDto.class);

	}

	@Override
	public void deletePayment(Long paymentId) {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new IllegalArgumentException("No Payment found with Id" + paymentId));
		paymentRepository.deleteById(paymentId);

	}

}
