package com.example.warehouse.controller;


import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
@CrossOrigin(origins = "*")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    //Get/api/warehouse
    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouse() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    //Get/api/warehouse/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Integer id) {
        return warehouseService.getWarehouseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Post/api/warehouse/crate
    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse created = warehouseService.createWarehouse(warehouse);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //Post/api/warehouse/update
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Integer id, @RequestBody Warehouse warehouse) {
        try {
            Warehouse updated = warehouseService.updateWarehouse(id, warehouse);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Post/api/warehouse/delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Integer id) {
        try {
            warehouseService.deleteWarehouse(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Get /api/warehouse/By Name
    @GetMapping("/searchByName")
    public ResponseEntity<List<Warehouse>> searchByName(@RequestParam String name) {
        List<Warehouse> warehouses = warehouseService.findWarehouseByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(warehouses);

    }

    //Get / api/warehouse/By capacity
    @GetMapping("/searchByCapacity")
    public ResponseEntity<List<Warehouse>> searchByCapacity(@RequestParam BigDecimal capacity) {
        List<Warehouse> warehouses = warehouseService.findWarehouseByCapacityGreaterThan(capacity);
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/ManagerName")
    public ResponseEntity<List<Warehouse>> getByManagerName() {
        List<Warehouse> warehouses = warehouseService.findWarehouseByManagerName();
        return ResponseEntity.ok(warehouses);
    }

}