package com.example.warehouse.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data

public class ProductResponseDTO{
        String name ;
        String description;
        BigDecimal price ;
        String warehouseName;
        Integer stockLevel ;


}
