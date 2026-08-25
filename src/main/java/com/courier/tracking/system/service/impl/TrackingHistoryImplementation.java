package com.courier.tracking.system.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.courier.tracking.system.entity.TrackingHistory;
import com.courier.tracking.system.repository.TrackingHistoryRepository;
import com.courier.tracking.system.responsedto.TrackingHistoryResponseDto;
import com.courier.tracking.system.service.TrackingHistoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrackingHistoryImplementation implements TrackingHistoryService {

	private final TrackingHistoryRepository trackingHistoryRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<TrackingHistoryResponseDto> getAllTrackingHistory() {
		List<TrackingHistory> aList = trackingHistoryRepository.findAll();
		if (aList.isEmpty()) {
			throw new IllegalArgumentException("No TrackingHistory");
		}
		return aList.stream().map(value -> modelMapper.map(value, TrackingHistoryResponseDto.class)).toList();
	}

	@Override
	public TrackingHistoryResponseDto getById(Long trackingId) {
		TrackingHistory trackingHistory = trackingHistoryRepository.findById(trackingId)
				.orElseThrow(() -> new IllegalArgumentException("No Tracking History with Id" + trackingId));
		return modelMapper.map(trackingHistory, TrackingHistoryResponseDto.class);
	}

	@Override
	public List<TrackingHistoryResponseDto> getByTrackingNumber(String trackingNumber) {

		List<TrackingHistory> trackingHistory = trackingHistoryRepository.findByShipment_TrackingNumber(trackingNumber);

		if (trackingHistory.isEmpty()) {
			throw new IllegalArgumentException("No tracking history found for tracking number: " + trackingNumber);
		}

		return trackingHistory.stream().map(history -> modelMapper.map(history, TrackingHistoryResponseDto.class))
				.toList();
	}

}
