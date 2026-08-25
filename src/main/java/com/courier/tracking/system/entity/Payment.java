package com.courier.tracking.system.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
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
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;
	
	@NotNull(message = "Payment amount is required")
	@Column(nullable = false , updatable = false)
	private Double amount;
	
	  @NotNull(message = "Payment method is required")
	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false, updatable = false)
	    private PaymentMethod paymentMethod;

	    @NotNull(message = "Payment status is required")
	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private PaymentStatus paymentStatus;

	    @CreationTimestamp
	    @Column(nullable = false, updatable = false)
	    private LocalDateTime paymentDateTime;

	    @OneToOne(mappedBy = "payment")
	    private Shipment shipment;

}
