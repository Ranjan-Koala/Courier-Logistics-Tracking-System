package com.courier.tracking.system.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMin;
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
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentId;

    @NotBlank(message = "Tracking number is required")
    @Column(nullable = false, unique = true, updatable = false)
    private String trackingNumber;

    @NotBlank(message = "Source is required")
    @Column(nullable = false)
    private String source;

    @NotBlank(message = "Destination is required")
    @Column(nullable = false)
    private String destination;

    @NotNull(message = "Weight is required")
    @DecimalMin(value = "0.01", message = "Weight must be greater than 0")
    @Column(nullable = false, updatable = false)
    private BigDecimal weight;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime shipmentDateTime;

    private LocalDateTime deliveryDate;

    @NotNull(message = "Shipment status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus shipmentStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY,
    cascade = CascadeType.PERSIST)
    @JoinColumn(name = "payment_id", unique = true)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "delivery_agent_id")
    private DeliveryAgent deliveryAgent;

    @OneToOne(fetch = FetchType.LAZY,
    cascade = CascadeType.PERSIST)
    @JoinColumn(name = "package_id", unique = true)
    private PackageEntity packageEntity;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @OneToMany(mappedBy = "shipment" , fetch = FetchType.LAZY,
    	    cascade = CascadeType.ALL,orphanRemoval = true)
    private List<TrackingHistory> trackingHistory = new ArrayList<>();
}