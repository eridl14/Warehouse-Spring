package com.example.warehouse.repository;

import com.example.warehouse.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Page<Warehouse> findByNameContainingIgnoreCase(Pageable pageable,String name);
    Page<Warehouse> findByCapacityGreaterThan(Pageable pageable, BigDecimal capacity);
    @Query("SELECT w FROM Warehouse w where w.managerName is not null")
    Page<Warehouse> findWarehouseByManagerName(Pageable pageable);

}
