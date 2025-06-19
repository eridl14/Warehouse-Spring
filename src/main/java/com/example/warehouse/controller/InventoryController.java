package com.example.warehouse.controller;
import java.util.List;
import com.example.warehouse.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.warehouse.entity.Inventory;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")

public class InventoryController {
    private final InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAll() {
        List<Inventory> inventories = inventoryService.findAllInventory();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{warehouseId}/{productId}")
    public ResponseEntity<Inventory> getByInventoryIdAndProductId(@PathVariable Integer warehouseId , @PathVariable Integer productId) {
        return inventoryService.findInventoryByWarehouseIdAndProductId(warehouseId,productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory createdInventory = inventoryService.createInventory(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInventory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Integer id, @RequestBody Inventory inventory) {
        try {
            Inventory updatedInventory = inventoryService.updateInventory(id, inventory);
            return ResponseEntity.ok(updatedInventory);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
            public ResponseEntity<Inventory> deleteInventory(@PathVariable Integer id){
            try {
                inventoryService.deleteInventory(id);
                return ResponseEntity.noContent().build();
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }

        }
        @GetMapping("/searchBywarehouseId")
        public ResponseEntity<List<Inventory>> searchByWarehouseId(@RequestParam Integer warehouseId) {
        List<Inventory> inventories   = inventoryService.findAllInventoryByWarehouseId(warehouseId);
        return ResponseEntity.ok(inventories);
        }

        @GetMapping("/searchByproductId")
public ResponseEntity<List<Inventory>> searchByProductId(@RequestParam Integer productId) {
    List<Inventory> inventories = inventoryService.findInventoryByProductId(productId);
    return ResponseEntity.ok(inventories);
    }
    @GetMapping("/LowStockItems")
    public ResponseEntity<List<Inventory>> getLowStockItems() {
        List<Inventory> inventories = inventoryService.findInventoryByLowStockItems();
        return ResponseEntity.ok(inventories);
    }
}






