package com.courier.tracking.system.responsedto;


import com.courier.tracking.system.entity.PackageType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageResponseDto {

    private Long id;

    private PackageType packageType;

    private Boolean fragile;

    private String dimensions;
}