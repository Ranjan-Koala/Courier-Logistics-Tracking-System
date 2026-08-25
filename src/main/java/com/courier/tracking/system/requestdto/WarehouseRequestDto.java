package com.courier.tracking.system.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseRequestDto {

    @NotBlank(message = "Warehouse name is required")
    @Size(min = 2, max = 100, message = "Warehouse name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Location is required")
    @Size(max = 255, message = "Location cannot exceed 255 characters")
    private String location;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be greater than zero")
    private Integer capacity;

    @NotBlank(message = "Contact number is required")
    @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Contact number must be a valid 10-digit Indian mobile number"
    )
    private String contactNo;
}