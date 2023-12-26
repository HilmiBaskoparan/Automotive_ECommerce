package com.hilmibaskoparan.business.responses.shoppingCartResponses;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateShoppingCartResponse {

	private BigDecimal totalAmount;
	private BigDecimal discountedTotalAmount;

	List<ShoppingCardItemDto> shoppingCardItemDtos;



}
