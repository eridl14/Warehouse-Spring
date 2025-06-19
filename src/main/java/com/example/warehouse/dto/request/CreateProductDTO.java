package com.example.warehouse.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductDTO(
        @NotNull(message = "The price cant be null")
        BigDecimal price,
        @Size(max=50, message = "The characters must be up to 50 ")
        String description,
        @NotBlank(message = "The category cant be empty")
        String category,
        @NotNull(message = "The weight cant be null")
        BigDecimal weight) {
}
