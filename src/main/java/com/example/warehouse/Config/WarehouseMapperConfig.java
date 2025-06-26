package com.example.warehouse.Config;
import com.example.warehouse.dto.response.InventoryResponseDTO;
import com.example.warehouse.entity.Inventory;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.entity.Product;
import com.example.warehouse.dto.response.WarehouseResponseDTO;
import com.example.warehouse.dto.response.ProductResponseDTO;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.List;

@Configuration
public class WarehouseMapperConfig {
    @Bean
    public ModelMapper warehouseMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Warehouse.class, WarehouseResponseDTO.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getProducts() != null ? src.getProducts().size() : 0,
                            WarehouseResponseDTO::setCurrentProductCount);
                    mapper.map(src -> src.getAddress() + ", " + src.getCity(),
                            WarehouseResponseDTO::setFullLocation);
                });

        modelMapper.typeMap(Product.class, ProductResponseDTO.class)
                .addMappings(mapper -> {
                    mapper.map(src -> {
                                List<Warehouse> ws = src.getWarehouses();
                                if (ws == null) return List.of();
                                return ws.stream()
                                        .map(Warehouse::getName)
                                        .filter(java.util.Objects::nonNull)
                                        .collect(Collectors.toList());
                            },
                            ProductResponseDTO::setWarehouseName);
                    mapper.map(src -> calculateStockLevel(src),
                            ProductResponseDTO::setStockLevel);
                });

        Condition<Integer, Integer> hasStock = ctx ->
                ctx.getSource() != null && ctx.getSource() > 0;
        modelMapper.typeMap(Inventory.class, InventoryResponseDTO.class)
                .addMappings(mapper -> {
                    mapper.when(hasStock)
                            .map(Inventory::getQuantity, InventoryResponseDTO::setQuantity);
                });

        Converter<LocalDateTime, String> dateTimeToString =
                new AbstractConverter<LocalDateTime, String>() {
                    protected String convert(LocalDateTime source) {
                        return source == null ? null :
                                source.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    }
                };
        modelMapper.addConverter(dateTimeToString);

        return modelMapper;
    }

    private int calculateStockLevel(Product product) {
        if (product.getInventories() == null) return 0;
        return product.getInventories().stream()
                .mapToInt(Inventory::getQuantity)
                .sum();
    }
}
