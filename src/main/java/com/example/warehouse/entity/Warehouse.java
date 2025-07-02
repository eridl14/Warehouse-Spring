package com.example.warehouse.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warehouse")
public class Warehouse extends BaseEntity {
    @Column(length = 50)
    private String address;
    @Column(precision = 10 ,scale = 2)
    private BigDecimal capacity;
    @Column(length = 20)
    private String managerName;
    @Enumerated(EnumType.STRING)
    private WarehouseStatus status;
    private String city;
    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY)
    private List<Inventory> inventories = new java.util.ArrayList<>();

    public List<Product> getProducts() {
        if (inventories == null) return List.of();
        return inventories.stream()
                .map(Inventory::getProduct)
                .distinct()
                .toList();
    }
}