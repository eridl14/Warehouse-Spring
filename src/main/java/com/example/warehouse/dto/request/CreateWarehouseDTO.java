package com.example.warehouse.dto.request;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CreateWarehouseDTO(
        @NotBlank(message = "Name is required")
        @Size(max = 50 , message = "Name cannot exceed 50 characters")
        String name,
        @NotBlank(message = "Location is required")
        String address,
        @Min(value = 1, message = "Capacity must be positive")
        BigDecimal capacity,
        @NotBlank(message = "managerName is required")
        @Size(max=20 , message = "managerName cannot exceed 20 characters")
        String managerName
) {}

