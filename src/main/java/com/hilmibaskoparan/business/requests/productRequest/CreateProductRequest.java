package com.hilmibaskoparan.business.requests.productRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreateProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private BigDecimal price;
    @NotNull
    private int quantity;
    @NotNull
    private int categoryId;
    @NotNull
    private int brandId;

}
