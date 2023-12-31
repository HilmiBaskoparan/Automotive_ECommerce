package com.hilmibaskoparan.business.requests.productRequest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddImageForProductRequest {

    @NotNull
    private int productId;
}
