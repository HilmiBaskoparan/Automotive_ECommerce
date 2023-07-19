package com.hilmibaskoparan.dto;

import jakarta.validation.constraints.NotEmpty;
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
public class BasketDTO {

    @NotEmpty(message = "")     // basket.productName.validation.constraints.NotNull.message
    private String productName;

    @NotEmpty
    private int amount;

    @NotEmpty
    private double price;

    @NotEmpty
    private UserDTO userDTO;
}
