package com.example.warehouse.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateProductDTO (
        @NotNull(message = "Warehouse id is required")
        Integer id,
        String name,
        BigDecimal price,
        String description,
        String Category ,
        BigDecimal weight
) {
}
