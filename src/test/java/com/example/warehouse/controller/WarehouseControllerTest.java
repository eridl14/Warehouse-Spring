package com.example.warehouse.controller;

import com.example.warehouse.dto.request.CreateWarehouseDTO;
import com.example.warehouse.dto.response.WarehouseResponseDTO;
import com.example.warehouse.entity.WarehouseStatus;
import com.example.warehouse.service.WarehouseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@WebMvcTest(WarehouseController.class)
class WarehouseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WarehouseService warehouseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createWarehouse_ValidInput_ReturnsCreated() throws Exception {

        CreateWarehouseDTO createWarehouseDTO = new CreateWarehouseDTO(
                "TestName", "TestLocation", new BigDecimal(1000), "TestCity"
        );

        WarehouseResponseDTO responseDTO = new WarehouseResponseDTO(
                1,
                "Test Warehouse",
                "Springfield",
                BigDecimal.valueOf(50000),
                WarehouseStatus.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now(),
                0,
                null,
                "Springfield, USA",
                "Springfield"
        );

        when(warehouseService.createWarehouse(any(CreateWarehouseDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/api/warehouse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createWarehouseDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Warehouse"))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    void getAllWarehouses_ValidInput_ReturnsBadRequest() throws Exception {
        // ΔΙΟΡΘΩΣΗ: 4 ορίσματα στον constructor
        CreateWarehouseDTO createWarehouseDTO = new CreateWarehouseDTO(
                "", "Springfield", BigDecimal.valueOf(-1), "TestCity"
        );

        mockMvc.perform(post("/api/warehouse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createWarehouseDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(greaterThan(0))));
    }
}
