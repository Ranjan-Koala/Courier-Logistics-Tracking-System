package com.courier.tracking.system.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.courier.tracking.system.entity.PaymentMethod;
import com.courier.tracking.system.entity.PaymentStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {

    private Long id;

    private Double amount;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private LocalDateTime paymentDateTime;
}