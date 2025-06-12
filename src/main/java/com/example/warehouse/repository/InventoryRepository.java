package com.example.warehouse.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.warehouse.entity.Invetory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Invetory, Integer>{
    List<Invetory> findByWarehouseId(Integer warehouseId);
    List<Invetory> findByProductId(Integer productId);
    Optional<Invetory> findByWarehouseIdAndProductId(Integer warehouseId , Integer productId);
    @Query("select i from Invetory i where i.quantity< i.minimumStock")
    List<Invetory> findLowStockItems();

}
