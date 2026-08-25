package com.courier.tracking.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.courier.tracking.system.entity.DeliveryAgent;
import com.courier.tracking.system.repository.DeliveryAgentRepository;
import com.courier.tracking.system.requestdto.DeliveryAgentRequestDto;
import com.courier.tracking.system.responsedto.DeliveryAgentResponseDto;
import com.courier.tracking.system.service.DeliveryAgentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryAgentImplementation implements DeliveryAgentService {

	private final DeliveryAgentRepository deliveryAgentRepository;
	private final ModelMapper modelMapper;

	@Override
	public DeliveryAgentResponseDto createAgentResponseDto(DeliveryAgentRequestDto deliveryAgentRequestDto) {
		DeliveryAgent deliveryAgent = modelMapper.map(deliveryAgentRequestDto, DeliveryAgent.class);
		DeliveryAgent saveDeliveryAgent = deliveryAgentRepository.save(deliveryAgent);
		return modelMapper.map(saveDeliveryAgent, DeliveryAgentResponseDto.class);
	}

	@Override
	public List<DeliveryAgentResponseDto> getAllAgent() {
		List<DeliveryAgent> deliveryAgent = deliveryAgentRepository.findAll();
		return deliveryAgent.stream().map(agent -> modelMapper.map(agent, DeliveryAgentResponseDto.class)).toList();
	}

	@Override
	public DeliveryAgentResponseDto getById(Long deliveryAgentId) {
		DeliveryAgent deliveryAgent = deliveryAgentRepository.findById(deliveryAgentId)
				.orElseThrow(() -> new IllegalArgumentException("DeliveryAgent is not found" + deliveryAgentId));
		return modelMapper.map(deliveryAgent, DeliveryAgentResponseDto.class);
	}

	@Override
	public DeliveryAgentResponseDto getByVehichleNumber(String vehicleNumber) {
		DeliveryAgent deliveryAgent = deliveryAgentRepository.findByVehicleNumber(vehicleNumber)
				.orElseThrow(() -> new IllegalArgumentException("VehichleNumber is not found" + vehicleNumber));
		return modelMapper.map(deliveryAgent, DeliveryAgentResponseDto.class);
	}

	@Override
	public DeliveryAgentResponseDto getByContact(String deliveryPhone) {
		DeliveryAgent deliveryAgent = deliveryAgentRepository.findByDeliveryPhone(deliveryPhone)
				.orElseThrow(() -> new IllegalArgumentException("VehichleNumber is not found" + deliveryPhone));
		return modelMapper.map(deliveryAgent, DeliveryAgentResponseDto.class);
	}

	@Override
	public List<DeliveryAgentResponseDto> getByMinimumRating(Double rating) {
		List<DeliveryAgent> deliveryAgent = deliveryAgentRepository.findByRatingGreaterThanEqual(rating);
		List<DeliveryAgent> deliveryAgents = new ArrayList<>();
		if (deliveryAgent.isEmpty()) {

			throw new IllegalArgumentException("No DeliveryAgent with Rating" + rating);
		}
		return deliveryAgents.stream().map(agents -> modelMapper.map(agents, DeliveryAgentResponseDto.class)).toList();

	}

	@Override
	public DeliveryAgentResponseDto updateAgentResponseDto(Long deliveryId, Map<String, Object> updates) {
		DeliveryAgent deliveryAgent = deliveryAgentRepository.findById(deliveryId)
				.orElseThrow(() -> new IllegalArgumentException("DeliveryAgent is not found" + deliveryId));
		updates.forEach((field, value) -> {
			switch (field) {
			case "name" -> deliveryAgent.setName((String) value);
			case "vehichleNumber" -> deliveryAgent.setVehicleNumber((String) value);
			case "rating" -> deliveryAgent.setRating(((Number) value).doubleValue());
			case "deliveryPhone" -> deliveryAgent.setDeliveryPhone((String) value);
			default -> throw new IllegalArgumentException("Invalid field: " + field);
			}
		});

		DeliveryAgent deliveryAgent2 = deliveryAgentRepository.save(deliveryAgent);
		return modelMapper.map(deliveryAgent2, DeliveryAgentResponseDto.class);
	}

	@Override
	public void deleteDeliveryAgent(Long deliveryId) {
		DeliveryAgent deliveryAgent = deliveryAgentRepository.findById(deliveryId)
				.orElseThrow(() -> new IllegalArgumentException("DeliveryAgent is not found" + deliveryId));
		deliveryAgentRepository.deleteById(deliveryId);

	}

	@Override
	public DeliveryAgentResponseDto updateAgentAvailablity(Long deliveryId, Map<String, Object> updates) {
		DeliveryAgent deliveryAgent = deliveryAgentRepository.findById(deliveryId)
				.orElseThrow(() -> new IllegalArgumentException("DeliveryAgent is not found" + deliveryId));
		updates.forEach((field, value) -> {
			switch (field) {
			case "agentAvailability" -> deliveryAgent.setAvailabilityStatus((Boolean) value);
			default -> throw new IllegalArgumentException("Invalid field: " + field);
			}
		});
		DeliveryAgent deliveryAgent2 = deliveryAgentRepository.save(deliveryAgent);
		return modelMapper.map(deliveryAgent2, DeliveryAgentResponseDto.class);

	}

}
