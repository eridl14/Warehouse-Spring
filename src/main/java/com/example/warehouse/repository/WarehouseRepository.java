package com.example.warehouse.repository;

import com.example.warehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    List<Warehouse> findByNameContainingIgnoreCase(String name);
    List<Warehouse> findByCapacityGreaterThan(BigDecimal capacity);
    @Query("SELECT w FROM Warehouse w where w.managerName is not null")
    List<Warehouse> findByManagerName();
}
