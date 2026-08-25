package com.courier.tracking.system.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAgentResponseDto {

    private Long id;

    private String name;

    private String phone;

    private String vehicleNo;

    private Boolean availabilityStatus;

    private Double rating;
}