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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotBlank(message = "Customer name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Customer email address is required")
    @Email(message = "Invalid email address")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Customer mobile number is required")
    @Pattern(
        regexp = "^\\d{10}$",
        message = "Customer mobile number must contain exactly 10 digits"
    )
    @Column(nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @NotBlank(message = "Customer address is required")
    @Column(nullable = false)
    private String address;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "customer")
    private List<Shipment> shipments = new ArrayList<>();
}