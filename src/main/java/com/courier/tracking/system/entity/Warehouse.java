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
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    @NotBlank(message = "Warehouse name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Warehouse location is required")
    @Column(nullable = false, updatable = false)
    private String location;

    @NotNull(message = "Warehouse capacity is required")
    @Min(value = 1, message = "Warehouse capacity must be greater than 0")
    @Column(nullable = false)
    private Integer capacity;

    @NotBlank(message = "Contact number is required")
    @Pattern(
        regexp = "^\\d{10}$",
        message = "Contact number must contain exactly 10 digits"
    )
    @Column(nullable = false, unique = true, length = 10)
    private String contactNumber;

    @OneToMany(mappedBy = "warehouse" , fetch = FetchType.LAZY)
    private List<Shipment> shipments = new ArrayList<>();
}