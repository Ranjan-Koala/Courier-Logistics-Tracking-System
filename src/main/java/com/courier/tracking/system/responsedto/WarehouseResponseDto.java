package com.courier.tracking.system.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseResponseDto {

    private Long id;

    private String name;

    private String location;

    private Integer capacity;

    private String contactNo;
}