package com.example.warehouse.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventory" , uniqueConstraints = @UniqueConstraint(columnNames = {"warehouse_id","product_id"}))

public class Inventory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Product product;

    private Integer quantity;
    @Column(name = "minimum_stock")
    private Integer minimumStock;
    @Column(name = "maximum_stock")
    private Integer maximumStock;
    @Column(name = "last_restocked")
    private LocalDateTime lastRestocked;

}
