package com.example.warehouse.service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import com.example.warehouse.entity.Invetory;

public interface InvetoryService {
    List<Invetory> findAllInvetory();
    Optional<Invetory> findInvetoryByWarehouseIdAndProductId(Integer warehouseId , Integer productId);
    Invetory createInvetory(Invetory invetory);
    Invetory updateInvetory(Integer id, Invetory invetory);
    void deleteInvetory(Integer id);

    List<Invetory> findAllInvetoryByWarehouseId(Integer warehouseId);
    List<Invetory> findInvetoryByProductId(Integer productId);
    List<Invetory> findInvetoryByLowStockItems();


}
