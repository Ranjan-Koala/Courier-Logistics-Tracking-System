package com.courier.tracking.system.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.courier.tracking.system.entity.ShipmentStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackingHistoryResponseDto {

    private Long id;

    private String location;

    private String remarks;

    private ShipmentStatus status;

    private LocalDateTime timestamp;
}