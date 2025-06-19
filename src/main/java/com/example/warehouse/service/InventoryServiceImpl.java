package com.example.warehouse.service;
import java.util.List;

import com.example.warehouse.entity.Inventory;
import org.springframework.stereotype.Service;
import com.example.warehouse.repository.InventoryRepository;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService{

  private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    @Override
    public List<Inventory> findAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> findInventoryByWarehouseIdAndProductId(Integer warehouseId, Integer productId) {
        return inventoryRepository.findByWarehouseIdAndProductId(warehouseId, productId);
    }

    @Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateInventory(Integer id, Inventory inventory) {
        return inventoryRepository.findById(id)
                .map(existing -> {
                    existing.setName(inventory.getName());
                    existing.setQuantity(inventory.getQuantity());
                    existing.setMinimumStock(inventory.getMinimumStock());
                    existing.setMaximumStock(inventory.getMaximumStock());
                    existing.setLastRestocked(inventory.getLastRestocked());
                    return inventoryRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Warehouse not found: " + id));
    }

    @Override
    public void deleteInventory(Integer id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public List<Inventory> findAllInventoryByWarehouseId(Integer warehouseId) {
        return inventoryRepository.findByWarehouseId(warehouseId);
    }

    @Override
    public List<Inventory> findInventoryByProductId(Integer productId) {
        return inventoryRepository.findByProductId(productId);
    }

    @Override
    public List<Inventory> findInventoryByLowStockItems(){
        return inventoryRepository.findLowStockItems();
    }
}
