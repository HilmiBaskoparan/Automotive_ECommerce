package com.hilmibaskoparan.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class ProductDTO {

    @NotNull
    private String productName;

    private String description;

    @NotNull
    private double price;

    @NotNull
    private int stock;
}
