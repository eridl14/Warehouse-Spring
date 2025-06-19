package com.example.warehouse.dto.response;

import java.math.BigDecimal;

public record WarehouseResponseDTO(
      String name,
      String address,
      BigDecimal capacity
) {
}
