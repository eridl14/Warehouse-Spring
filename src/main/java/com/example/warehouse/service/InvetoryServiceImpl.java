package com.example.warehouse.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.warehouse.entity.Invetory;
import com.example.warehouse.repository.InventoryRepository;
import java.util.Optional;

@Service

public class InvetoryServiceImpl implements InvetoryService {

    private final InventoryRepository invetoryRepository;
    public InvetoryServiceImpl(InventoryRepository invetoryRepository) {
        this.invetoryRepository = invetoryRepository;
    }


    @Override
    public List<Invetory> findAllInvetory() {
        return invetoryRepository.findAll();
    }

    @Override
    public Optional<Invetory> findInvetoryByWarehouseIdAndProductId(Integer warehouseId , Integer productId){
        return invetoryRepository.findByWarehouseIdAndProductId(warehouseId, productId);
    }

    @Override
    public Invetory createInvetory(Invetory invetory) {
        return invetoryRepository.save(invetory);
    }

    @Override
    public Invetory updateInvetory(Integer id, Invetory invetory) {
      return invetoryRepository.findById(id)
              .map(existing -> {
                  existing.setName(invetory.getName());
                  existing.setQuantity(invetory.getQuantity());
                  existing.setMinimumStock(invetory.getMinimumStock());
                  existing.setMaximumStock(invetory.getMaximumStock());
                  existing.setLastRestocked(invetory.getLastRestocked());
                  return invetoryRepository.save(existing);
              })
              .orElseThrow(() -> new RuntimeException("Warehouse not found: " + id));
    }

    @Override
    public void deleteInvetory(Integer id){
        invetoryRepository.deleteById(id);
    }

    @Override
    public List<Invetory> findAllInvetoryByWarehouseId(Integer warehouseId) {
        return invetoryRepository.findByWarehouseId(warehouseId);
    }

    @Override
    public List<Invetory> findInvetoryByProductId(Integer productId) {
        return invetoryRepository.findByProductId(productId);
    }
    @Override
    public List<Invetory> findInvetoryByLowStockItems(){
        return invetoryRepository.findLowStockItems();
    }



}
