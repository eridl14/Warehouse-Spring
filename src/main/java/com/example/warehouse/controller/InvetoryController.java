package com.example.warehouse.controller;
import java.util.List;
import com.example.warehouse.service.InvetoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.warehouse.entity.Invetory;

@RestController
@RequestMapping("/api/invetory")
@CrossOrigin(origins = "*")

public class InvetoryController {
    private final InvetoryService invetoryService;
    public InvetoryController(InvetoryService invetoryService) {
        this.invetoryService = invetoryService;
    }

    @GetMapping
    public ResponseEntity<List<Invetory>> getAll() {
        List<Invetory> invetories = invetoryService.findAllInvetory();
        return ResponseEntity.ok(invetories);
    }

    @GetMapping("/{warehouseId}/{productId}")
    public ResponseEntity<Invetory> getByInvetoryIdAndProductId(@PathVariable Integer warehouseId , @PathVariable Integer productId) {
        return invetoryService.findInvetoryByWarehouseIdAndProductId(warehouseId,productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Invetory> createInvetory(@RequestBody Invetory invetory) {
        Invetory createdInvetory = invetoryService.createInvetory(invetory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvetory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invetory> updateInvetory(@PathVariable Integer id, @RequestBody Invetory invetory) {
        try {
            Invetory updatedInvetory = invetoryService.updateInvetory(id, invetory);
            return ResponseEntity.ok(updatedInvetory);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
            public ResponseEntity<Invetory> deleteInvetory(@PathVariable Integer id){
            try {
                invetoryService.deleteInvetory(id);
                return ResponseEntity.noContent().build();
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }

        }
        @GetMapping("/searchBywarehouseId")
        public ResponseEntity<List<Invetory>> searchByWarehouseId(@RequestParam Integer warehouseId) {
        List<Invetory> invetories   = invetoryService.findAllInvetoryByWarehouseId(warehouseId);
        return ResponseEntity.ok(invetories);
        }

        @GetMapping("/searchByproductId")
public ResponseEntity<List<Invetory>> searchByProductId(@RequestParam Integer productId) {
    List<Invetory> invetories = invetoryService.findInvetoryByProductId(productId);
    return ResponseEntity.ok(invetories);
    }
    @GetMapping("/LowStockItems")
    public ResponseEntity<List<Invetory>> getLowStockItems() {
        List<Invetory> invetories = invetoryService.findInvetoryByLowStockItems();
        return ResponseEntity.ok(invetories);
    }
}






