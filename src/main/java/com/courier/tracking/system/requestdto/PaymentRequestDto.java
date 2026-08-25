package com.courier.tracking.system.requestdto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.courier.tracking.system.entity.PaymentMethod;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;
}