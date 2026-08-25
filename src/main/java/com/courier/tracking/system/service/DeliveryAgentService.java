package com.courier.tracking.system.service;

import java.util.List;
import java.util.Map;

import com.courier.tracking.system.requestdto.DeliveryAgentRequestDto;
import com.courier.tracking.system.responsedto.DeliveryAgentResponseDto;

public interface DeliveryAgentService {
	
	DeliveryAgentResponseDto createAgentResponseDto(DeliveryAgentRequestDto deliveryAgentRequestDto);
	
	List<DeliveryAgentResponseDto> getAllAgent();
	
	DeliveryAgentResponseDto getById(Long deliveryAgentId);
	
	DeliveryAgentResponseDto getByVehichleNumber(String vehicleNumber);
	
	DeliveryAgentResponseDto getByContact(String deliveryPhone);
	
	List<DeliveryAgentResponseDto> getByMinimumRating(Double rating);
	
	DeliveryAgentResponseDto updateAgentResponseDto(Long deliveryId, Map<String, Object> updates);
	
	void deleteDeliveryAgent(Long deliveryId);
	
	DeliveryAgentResponseDto updateAgentAvailablity(Long deliveryId, Map<String, Object> updates);

}
