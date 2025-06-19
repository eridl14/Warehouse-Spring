package com.example.warehouse.repository;
import com.example.warehouse.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer>{
    List<Inventory> findByWarehouseId(Integer warehouseId);
    List<Inventory> findByProductId(Integer productId);
    Optional<Inventory> findByWarehouseIdAndProductId(Integer warehouseId , Integer productId);
    @Query("select i from Inventory i where i.quantity< i.minimumStock")
    List<Inventory> findLowStockItems();

}
