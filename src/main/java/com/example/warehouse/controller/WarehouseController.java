package com.example.warehouse.controller;


import com.example.warehouse.dto.request.CreateWarehouseDTO;
import com.example.warehouse.dto.response.WarehouseResponseDTO;
import com.example.warehouse.service.WarehouseService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
    public ResponseEntity<Page<WarehouseResponseDTO>> getWarehouses(@ParameterObject Pageable pageable) {
        Page<WarehouseResponseDTO> warehouses = warehouseService.getAllWarehouses(pageable);
        return ResponseEntity.ok(warehouses);
    }

    //Get/api/warehouse/{id}
    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponseDTO> getWarehouseById(@PathVariable Integer id) {
        return warehouseService.getWarehouseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Post/api/warehouse/crate
    @PostMapping
    public ResponseEntity<WarehouseResponseDTO> createWarehouse(
            @Valid @RequestBody CreateWarehouseDTO createWarehouseDTO){
        WarehouseResponseDTO responseDTO = warehouseService.createWarehouse(createWarehouseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
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
    public ResponseEntity<Page<WarehouseResponseDTO>> searchByName(@ParameterObject Pageable pageable,@RequestParam String name) {
        Page<WarehouseResponseDTO> warehouses = warehouseService.findWarehouseByNameContainingIgnoreCase(pageable, name);
        return ResponseEntity.ok(warehouses);

    }

    //Get / api/warehouse/By capacity
    @GetMapping("/searchByCapacity")
    public ResponseEntity<Page<WarehouseResponseDTO>> searchByCapacity(@ParameterObject Pageable pageable, @RequestParam BigDecimal capacity) {
        Page<WarehouseResponseDTO> warehouses = warehouseService.findWarehouseByCapacityGreaterThan(pageable ,capacity);
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/ManagerName")
    public ResponseEntity<Page<WarehouseResponseDTO>> getByManagerName(@ParameterObject Pageable pageable) {
        Page<WarehouseResponseDTO> warehouses = warehouseService.findWarehouseByManagerName( pageable);
        return ResponseEntity.ok(warehouses);
    }
}