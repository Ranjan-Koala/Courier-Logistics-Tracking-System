package com.courier.tracking.system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courier.tracking.system.responsedto.TrackingHistoryResponseDto;
import com.courier.tracking.system.service.TrackingHistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tracking-history")
public class TrackingHistoryController {

    private final TrackingHistoryService trackingHistoryService;


    @GetMapping
    public ResponseEntity<List<TrackingHistoryResponseDto>> getAllTrackingHistory() {

        List<TrackingHistoryResponseDto> response =
                trackingHistoryService.getAllTrackingHistory();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{trackingId}")
    public ResponseEntity<TrackingHistoryResponseDto> getById(
            @PathVariable Long trackingId) {

        TrackingHistoryResponseDto response =
                trackingHistoryService.getById(trackingId);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/shipment/{trackingNumber}")
    public ResponseEntity<List<TrackingHistoryResponseDto>> getByTrackingNumber(
            @PathVariable String trackingNumber) {

        List<TrackingHistoryResponseDto> response =
                trackingHistoryService.getByTrackingNumber(trackingNumber);

        return ResponseEntity.ok(response);
    }
}