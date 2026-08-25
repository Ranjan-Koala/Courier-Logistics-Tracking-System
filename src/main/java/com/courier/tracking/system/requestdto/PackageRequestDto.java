package com.courier.tracking.system.requestdto;

import com.courier.tracking.system.entity.PackageType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageRequestDto {

    @NotNull(message = "Package type is required")
    private PackageType packageType;

    @NotNull(message = "Fragile status is required")
    private Boolean fragile;

    @NotNull(message = "Dimensions are required")
    @Size(max = 50, message = "Dimensions cannot exceed 50 characters")
    private String dimensions;
}