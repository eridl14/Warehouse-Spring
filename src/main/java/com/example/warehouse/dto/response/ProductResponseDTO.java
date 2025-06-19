package com.example.warehouse.dto.response;

import java.math.BigDecimal;

public record ProductResponseDTO(
        String name ,
        String description,
        BigDecimal price ) {
}
