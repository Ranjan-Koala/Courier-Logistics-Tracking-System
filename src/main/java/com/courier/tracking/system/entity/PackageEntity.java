package com.courier.tracking.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packageEntityId;

    @NotNull(message = "Package type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PackageType packageType;

    @NotNull(message = "Fragile status is required")
    @Column(nullable = false)
    private Boolean fragile;

    @NotBlank(message = "Package dimensions are required")
    @Column(nullable = false)
    private String dimension;

    @OneToOne(mappedBy = "packageEntity")
    private Shipment shipment;
}