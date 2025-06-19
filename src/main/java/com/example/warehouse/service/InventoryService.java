package com.example.warehouse.service;
import java.util.List;
import java.util.Optional;

import com.example.warehouse.entity.Inventory;

public interface InventoryService {
    List<Inventory> findAllInventory();
    Optional<Inventory> findInventoryByWarehouseIdAndProductId(Integer warehouseId , Integer productId);
    Inventory createInventory(Inventory inventory);
    Inventory updateInventory(Integer id, Inventory inventory);
    void deleteInventory(Integer id);

    List<Inventory> findAllInventoryByWarehouseId(Integer warehouseId);
    List<Inventory> findInventoryByProductId(Integer productId);
    List<Inventory> findInventoryByLowStockItems();


}
