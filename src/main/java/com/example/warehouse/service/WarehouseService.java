package com.example.warehouse.service;

import com.example.warehouse.entity.Warehouse;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface WarehouseService {
    List<Warehouse> getAllWarehouses();

    Optional<Warehouse> getWarehouseById(Integer Id);
    Warehouse createWarehouse(Warehouse warehouse);
    Warehouse updateWarehouse(Integer Id , Warehouse warehouse);
    void deleteWarehouse(Integer Id);

    List<Warehouse> findWarehouseByNameContainingIgnoreCase(String name);
    List<Warehouse> findWarehouseByCapacityGreaterThan(BigDecimal capacity);
    List<Warehouse> findWarehouseByManagerName();



}
