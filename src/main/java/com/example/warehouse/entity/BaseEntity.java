package com.example.warehouse.entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;


@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String name;
    @Column(nullable = false , name = "create_day")
    private LocalDateTime createDay;

    @PrePersist
    public void prePersist() {
        createDay = LocalDateTime.now();
    }

}
