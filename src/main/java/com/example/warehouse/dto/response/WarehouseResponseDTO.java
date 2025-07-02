package com.example.warehouse.dto.response;

import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.entity.WarehouseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class WarehouseResponseDTO implements Serializable {
      Integer id;
      @Size(max = 50, message = "Name cannot exceed 50 characters")
      String name;
      String address;
      BigDecimal capacity;
      @JsonFormat(shape = JsonFormat.Shape.STRING)
      WarehouseStatus status;
      LocalDateTime createdAt;
      LocalDateTime updatedAt;
      Integer currentProductCount;
      List<ProductResponseDTO> products;
      String fullLocation;
      String city;
      String managerName;

      public static WarehouseResponseDTO fromEntity(Warehouse entity) {
            if (entity == null) return null;
            WarehouseResponseDTO dto = new WarehouseResponseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setManagerName(entity.getManagerName());
            dto.setAddress(entity.getAddress());
            dto.setCity(entity.getCity());
            dto.setCapacity(entity.getCapacity());
            dto.setStatus(entity.getStatus());
            dto.setCreatedAt(entity.getCreatedAt() != null ? LocalDateTime.parse(entity.getCreatedAt().toString()) : null);
            dto.setUpdatedAt(entity.getUpdatedAt() != null ? LocalDateTime.parse(entity.getUpdatedAt().toString()) : null);
            dto.setCurrentProductCount(entity.getProducts() == null ? 0 : entity.getProducts().size());
            dto.setFullLocation(STR."\{entity.getAddress()}, \{entity.getCity()}");
            dto.setProducts(entity.getProducts() == null ? List.of() :
                    entity.getProducts().stream()
                            .map(ProductResponseDTO::fromEntity)
                            .collect(Collectors.toList())
            );
            return dto;
      }
}



