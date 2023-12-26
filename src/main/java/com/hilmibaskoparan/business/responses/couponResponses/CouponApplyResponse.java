package com.hilmibaskoparan.business.responses.couponResponses;

import java.math.BigDecimal;
import java.util.List;

import com.hilmibaskoparan.business.responses.shoppingCartResponses.ShoppingCardItemDto;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CouponApplyResponse {

	private BigDecimal totalAmount;
	private BigDecimal discountedTotalAmount;

	List<ShoppingCardItemDto> shoppingCardItemDtos;
}
