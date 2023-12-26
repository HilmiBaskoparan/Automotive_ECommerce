package com.hilmibaskoparan.business.requests.shoppingCardRequest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateShoppingCartRequest {

    @NotNull
    private int customerId;
    @NotNull
    private int productId;
    @NotNull
    private int quantity;
}
