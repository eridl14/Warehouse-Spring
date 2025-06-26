package com.example.warehouse.validation.Valid;

import com.example.warehouse.validation.Validator.WarehouseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WarehouseValidator.class)

public @interface ValidWarehouse {
    String message() default "Invalid warehouse capacity fro the specified type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


