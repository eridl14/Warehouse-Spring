package com.example.warehouse.dto.request;
import com.example.warehouse.entity.Warehouse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateWarehouseDTO {
        @NotBlank(message = "Name is required")
        @Size(max = 50, message = "Name cannot exceed 50 characters")
        String name;
        @NotBlank(message = "Location is required")
        String address;
        BigDecimal capacity;
        String city;

        public static Warehouse toEntity(CreateWarehouseDTO dto) {
                if (dto == null) return null;
                Warehouse entity = new Warehouse();
                entity.setName(dto.getName());
                entity.setAddress(dto.getAddress());
                entity.setCity(dto.getCity());

                entity.setCapacity(dto.getCapacity());
                return entity;
        }
}

