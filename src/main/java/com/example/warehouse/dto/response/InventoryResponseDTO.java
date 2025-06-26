package com.example.warehouse.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter

public class InventoryResponseDTO{
        String name;
        Integer quantity;
        LocalDateTime lastRestocked;
}
