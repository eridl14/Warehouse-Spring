package com.example.warehouse.Mappers;
import com.example.warehouse.dto.request.CreateInventoryDTO;
import com.example.warehouse.dto.response.InventoryResponseDTO;
import com.example.warehouse.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface InventoryMapper {

    @Mapping(source = "product.sku", target = "productSku")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "warehouse.code", target = "warehouseCode")
    @Mapping(target = "availableQuantity",
            expression = "java(entity.getTotalQuantity() - entity.getReservedQuantity())")
    InventoryResponseDTO toResponseDTO(Inventory entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Inventory toEntity(CreateInventoryDTO dto);
}
