package com.example.warehouse.service;

import com.example.warehouse.dto.request.CreateWarehouseDTO;
import com.example.warehouse.dto.response.WarehouseResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

public interface WarehouseService {
    WarehouseResponseDTO createWarehouse(CreateWarehouseDTO createWarehouseDTO);

    Page<WarehouseResponseDTO> getAllWarehouses(Pageable pageable);

    Optional<WarehouseResponseDTO> getWarehouseById(Integer id);

    void deleteWarehouse(Integer id);

    Page<WarehouseResponseDTO> findWarehouseByNameContainingIgnoreCase(Pageable pageable, String name);

    Page<WarehouseResponseDTO> findWarehouseByCapacityGreaterThan(Pageable pageable, BigDecimal capacity);

    Page<WarehouseResponseDTO> findWarehouseByManagerName(Pageable pageable);

}
