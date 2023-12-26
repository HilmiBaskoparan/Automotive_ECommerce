package com.hilmibaskoparan.business.requests.paymentRequest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {

    @NotNull
    private int customerId;

    @NotNull
    private int addressId;

    private String discountCode;

    @NotNull
    private BigDecimal totalAmount;

    private int creditCartId;
}
