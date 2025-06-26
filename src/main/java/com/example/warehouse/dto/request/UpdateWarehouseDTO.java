package com.example.warehouse.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateWarehouseDTO(
        @NotNull(message = "Warehouse id is required")
        Integer id,
        @Size(max=50, message = "Name cannot exceed 50 characters")
        String name,
        String location,
        @Min(value= 1, message = "Capacity must be positive")
        Integer capacity) {}
