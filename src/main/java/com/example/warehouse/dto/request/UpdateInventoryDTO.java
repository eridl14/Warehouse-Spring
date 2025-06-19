package com.example.warehouse.dto.request;

import java.time.LocalDateTime;

public record UpdateInventoryDTO(
        Integer id,
        Integer quantity,
        Integer minimumStock,
        Integer maximumStock,
        LocalDateTime lastRestocked){
}
