package com.example.warehouse.service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.repository.WarehouseRepository;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
public class WarehouseServiceImpl implements WarehouseService{

    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<Warehouse> getAllWarehouses(){
        return warehouseRepository.findAll();
    }
    @Override
    public Optional<Warehouse> getWarehouseById(Integer id){
        return warehouseRepository.findById(id);
    }
    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }
    @Override
    public Warehouse updateWarehouse(Integer id, Warehouse warehouse) {
        return warehouseRepository.findById(id)
        .map(existing -> {
            existing.setName(warehouse.getName());
            existing.setAddress(warehouse.getAddress());
            existing.setCapacity(warehouse.getCapacity());
            existing.setManagerName(warehouse.getManagerName());
            return warehouseRepository.save(existing);
        })
                .orElseThrow(() -> new RuntimeException("Warehouse not found: " + id));
    }

    @Override
    public void deleteWarehouse(Integer id) {
        warehouseRepository.deleteById(id);
    }

    @Override
    public List<Warehouse> findWarehouseByNameContainingIgnoreCase(String name) {
        return warehouseRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Warehouse> findWarehouseByCapacityGreaterThan(BigDecimal capacity) {
        return warehouseRepository.findByCapacityGreaterThan(capacity);
    }

    @Override
    public List<Warehouse> findWarehouseByManagerName() {
        return warehouseRepository.findByManagerName();
    }


}
