package com.example.warehouse.dto.request;

import java.time.LocalDateTime;

public record CreateInventoryDTO(
        String name,
        Integer warehouse_id,
        Integer product_id,
        Integer quantity,
        Integer minimumStock,
        Integer maximumStock,
        LocalDateTime lastRestocked) {
}
