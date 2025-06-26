package com.example.warehouse.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(nullable = false,precision = 10 ,scale = 2)
    private BigDecimal price;
    @Column(length = 50)
    private String description;
    @Column(nullable = false,length = 50)
    private String category;
    @Column(nullable = false,precision = 10 ,scale = 2)
    private BigDecimal weight;


        public List<Warehouse> getWarehouses() {
            return inventories == null ? List.of()
                    : inventories.stream().map(Inventory::getWarehouse).distinct().toList();
        }



    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Inventory> inventories = new ArrayList<>();

}
