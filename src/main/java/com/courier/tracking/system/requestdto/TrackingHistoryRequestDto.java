package com.courier.tracking.system.requestdto;

import com.courier.tracking.system.entity.ShipmentStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackingHistoryRequestDto {

    @NotBlank(message = "Location is required")
    @Size(max = 255, message = "Location cannot exceed 255 characters")
    private String location;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String remarks;

    @NotNull(message = "Shipment status is required")
    private ShipmentStatus status;
}