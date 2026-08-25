package com.courier.tracking.system.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.courier.tracking.system.entity.ShipmentStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentResponseDto {

    private Long id;

    private String trackingNumber;

    private String source;

    private String destination;

    private Double weight;

    private LocalDateTime shipmentDateTime;

    private LocalDate deliveryDate;

    private ShipmentStatus status;

    private Long customerId;

    private Long warehouseId;

    private Long deliveryAgentId;

    private PackageResponseDto packageDetails;

    private PaymentResponseDto paymentDetails;

    private List<TrackingHistoryResponseDto> trackingHistory;
}