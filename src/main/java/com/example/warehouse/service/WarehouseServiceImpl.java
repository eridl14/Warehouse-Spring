package com.example.warehouse.service;

import com.example.warehouse.dto.request.CreateWarehouseDTO;
import com.example.warehouse.dto.response.WarehouseResponseDTO;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.entity.WarehouseStatus;
import com.example.warehouse.repository.WarehouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository repository;

    public WarehouseServiceImpl(WarehouseRepository repository) {
        this.repository = repository;
    }

    public WarehouseResponseDTO createWarehouse(CreateWarehouseDTO createWarehouseDTO) {
        Warehouse warehouse = CreateWarehouseDTO.toEntity(createWarehouseDTO);
        warehouse.setStatus(WarehouseStatus.ACTIVE);
        warehouse.setCreatedAt(LocalDateTime.now());
        Warehouse savedWarehouse = repository.save(warehouse);
        return WarehouseResponseDTO.fromEntity(savedWarehouse);
    }

    public Page<WarehouseResponseDTO> getAllWarehouses(Pageable pageable) {
        Page<Warehouse> warehouses = repository.findAll(pageable);
        return warehouses.map(WarehouseResponseDTO::fromEntity);
    }

    public Optional<WarehouseResponseDTO> getWarehouseById(Integer id) {
        Optional<Warehouse> warehouseOpt = repository.findById(id);
        return warehouseOpt.map(WarehouseResponseDTO::fromEntity);
    }

    public void deleteWarehouse(Integer id){
        repository.deleteById(id);
    }

    public Page<WarehouseResponseDTO> findWarehouseByNameContainingIgnoreCase(Pageable pageable, String name) {
        Page<Warehouse> warehouses = repository.findByNameContainingIgnoreCase(pageable , name );
        return warehouses.map(WarehouseResponseDTO::fromEntity);
    }

    public Page<WarehouseResponseDTO> findWarehouseByCapacityGreaterThan(Pageable pageable, BigDecimal capacity){
        Page<Warehouse> warehouses = repository.findByCapacityGreaterThan(pageable, capacity);
        return warehouses.map(WarehouseResponseDTO::fromEntity);
    }

    public Page<WarehouseResponseDTO>  findWarehouseByManagerName(Pageable pageable){
        Page<Warehouse> warehouses = repository.findWarehouseByManagerName(pageable);
        return warehouses.map(WarehouseResponseDTO::fromEntity);
    }
}
