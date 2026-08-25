package com.courier.tracking.system.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String address;
}