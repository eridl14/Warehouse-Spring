package com.example.warehouse.dto.response;

import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.WarehouseStatus;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor

public class WarehouseResponseDTO {
      Integer id;
      @Size(max = 50, message = "Name cannot exceed 50 characters")
      String name;
      String location;
      BigDecimal capacity;
      WarehouseStatus status;
      LocalDateTime creatAt;
      LocalDateTime updateAt;
      int currentProductCount;
      List<Product> products;
      String fullLocation;
      String city;
}



