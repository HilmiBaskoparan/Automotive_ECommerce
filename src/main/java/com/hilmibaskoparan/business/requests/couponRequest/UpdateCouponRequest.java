package com.hilmibaskoparan.business.requests.couponRequest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UpdateCouponRequest {

    @NotNull
    private int id;

    @NotNull
    private BigDecimal discount;

    @NotNull
    private LocalDate expiresAt;

}
