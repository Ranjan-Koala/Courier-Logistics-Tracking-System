package com.courier.tracking.system.requestdto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentRequestDto {

    @NotBlank(message = "Source is required")
    @Size(max = 255, message = "Source cannot exceed 255 characters")
    private String source;

    @NotBlank(message = "Destination is required")
    @Size(max = 255, message = "Destination cannot exceed 255 characters")
    private String destination;

    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be greater than zero")
    private Double weight;

    @NotNull(message = "Customer ID is required")
    @Positive(message = "Customer ID must be greater than zero")
    private Long customerId;

    @NotNull(message = "Warehouse ID is required")
    @Positive(message = "Warehouse ID must be greater than zero")
    private Long warehouseId;

    @Positive(message = "Delivery agent ID must be greater than zero")
    private Long deliveryAgentId;

    @NotNull(message = "Package details are required")
    @Valid
    private PackageRequestDto packageDetails;

    @NotNull(message = "Payment details are required")
    @Valid
    private PaymentRequestDto paymentDetails;
}