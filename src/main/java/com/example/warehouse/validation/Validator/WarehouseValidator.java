package com.example.warehouse.validation.Validator;

import com.example.warehouse.dto.request.CreateWarehouseDTO;
import com.example.warehouse.validation.Valid.ValidWarehouse;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;

@Data

public class WarehouseValidator implements ConstraintValidator<ValidWarehouse, CreateWarehouseDTO> {
    @Override
    public boolean isValid(CreateWarehouseDTO dto, ConstraintValidatorContext context) {
        if (dto == null) return true;

        BigDecimal capacity = dto.getCapacity();
        String city = dto.getCity();

        // capacity μπορεί να είναι null
        if (capacity == null) return false;

        // min 100
        if (capacity.compareTo(BigDecimal.valueOf(100)) < 0) {
            return false;
        }

        // max 1_000_000
        if (capacity.compareTo(BigDecimal.valueOf(1_000_000)) > 0) {
            return false;
        }

        // Αν city = "US", τότε capacity >= 10_000
        return !"US".equals(city) || capacity.compareTo(BigDecimal.valueOf(10_000)) >= 0;
    }
}






