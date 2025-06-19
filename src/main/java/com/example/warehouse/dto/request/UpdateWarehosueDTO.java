package com.example.warehouse.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateWarehosueDTO(
        @NotNull(message = "Warehouse id is required")
        Integer id,
        @Size(max=50, message = "Name cannot exceed 50 characters")
        String name,
        String address,
        BigDecimal capacity)
{}
