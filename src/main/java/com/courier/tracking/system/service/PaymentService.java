package com.courier.tracking.system.service;

import java.util.List;
import java.util.Map;

import com.courier.tracking.system.responsedto.PackageResponseDto;
import com.courier.tracking.system.responsedto.PaymentResponseDto;

public interface PaymentService {
	
	List<PaymentResponseDto> getAllPayment();
	
	PaymentResponseDto getById(Long paymentId);
	
	PaymentResponseDto updateStatus(Long paymentId, Map<String, Object> updates);
	
	void deletePayment(Long paymentId);

}
