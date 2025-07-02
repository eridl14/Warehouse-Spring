package com.example.warehouse.dto.response;

import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Inventory;
import com.example.warehouse.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor


public class ProductResponseDTO {
    private Integer id;
    private String name;
    private int stockLevel;
    private List<String> warehouseNames;
    // Αν έχεις κι άλλα πεδία, τα βάζεις εδώ...

    // Getters & Setters
    // ...

    // Static method για mapping από entity σε DTO
    public static ProductResponseDTO fromEntity(Product entity) {
        if (entity == null) return null;
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        // Υπολογισμός stockLevel από inventories
        if (entity.getInventories() != null) {
            dto.setStockLevel(
                    entity.getInventories().stream()
                            .mapToInt(inv -> inv.getQuantity() == null ? 0 : inv.getQuantity())
                            .sum()
            );
        } else {
            dto.setStockLevel(0);
        }

        // Warehouses σε λίστα με ονόματα
        dto.setWarehouseNames(
                entity.getWarehouses() == null ? List.of() :
                        entity.getWarehouses().stream()
                                .map(Warehouse::getName)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList())
        );


        return dto;
    }
}
