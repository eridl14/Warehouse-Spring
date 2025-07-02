package com.example.warehouse.Mappers;

import com.example.warehouse.dto.request.CreateInventoryDTO;
import com.example.warehouse.dto.response.InventoryResponseDTO;
import com.example.warehouse.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {


    InventoryResponseDTO toResponseDTO(Inventory entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Inventory toEntity(CreateInventoryDTO dto);
}
