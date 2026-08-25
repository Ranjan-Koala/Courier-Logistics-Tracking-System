package com.courier.tracking.system.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryAgentId;

    @NotBlank(message = "Delivery agent name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Delivery phone number is required")
    @Pattern(
        regexp = "^\\d{10}$",
        message = "Delivery phone number must contain exactly 10 digits"
    )
    @Column(nullable = false, unique = true, length = 10)
    private String deliveryPhone;

    @NotBlank(message = "Vehicle number is required")
    @Column(nullable = false, unique = true)
    private String vehicleNumber;

    @NotNull(message = "Availability status is required")
    @Column(nullable = false)
    private Boolean availabilityStatus;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating cannot be less than 0")
    @Max(value = 5, message = "Rating cannot be greater than 5")
    @Column(nullable = false)
    private Double rating;

    @OneToMany(mappedBy = "deliveryAgent" ,  fetch = FetchType.LAZY)
    private List<Shipment> shipments = new ArrayList<>();
}