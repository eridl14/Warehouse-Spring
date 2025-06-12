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
@Table(name = "warehouse")
public class Warehouse extends BaseEntity {
    @Column(length = 50)
    private String address;
    @Column(precision = 10 ,scale = 2)
    private BigDecimal capacity;
    @Column(length = 20)
    private String managerName;

}
