package com.example.warehouse.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


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

}
