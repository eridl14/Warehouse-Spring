package com.example.warehouse.Config;

import com.example.warehouse.dto.response.InventoryResponseDTO;
import com.example.warehouse.dto.response.ProductResponseDTO;
import com.example.warehouse.dto.response.WarehouseResponseDTO;
import com.example.warehouse.entity.Inventory;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Warehouse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.config.Configuration;
import org.springframework.context.annotation.Bean;

public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setMethodAccessLevel(Configuration.AccessLevel.PUBLIC);

        configureTypeMaps(modelMapper);

        return modelMapper;
    }

    private void configureTypeMaps(ModelMapper modelMapper){
        modelMapper.createTypeMap(Warehouse.class, WarehouseResponseDTO.class);
        modelMapper.createTypeMap(Product.class, ProductResponseDTO.class);
        modelMapper.createTypeMap(Inventory.class, InventoryResponseDTO.class);
    }
}
