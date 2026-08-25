package com.courier.tracking.system.service;

import java.util.List;

import com.courier.tracking.system.responsedto.TrackingHistoryResponseDto;

public interface TrackingHistoryService {

	List<TrackingHistoryResponseDto> getAllTrackingHistory();
	
	TrackingHistoryResponseDto getById(Long trackingId);
	
    List<TrackingHistoryResponseDto> getByTrackingNumber(String trackingNumber);
}
