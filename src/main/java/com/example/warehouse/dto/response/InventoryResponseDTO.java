package com.example.warehouse.dto.response;

import java.time.LocalDateTime;

public record InventoryResponseDTO(
        String name,
        Integer quantity,
        LocalDateTime lastRestocked) {
}
