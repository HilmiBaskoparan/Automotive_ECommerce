package com.hilmibaskoparan.business.responses.shoppingCardResponses;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateShoppingCardResponse {

	private BigDecimal totalAmount;
	private BigDecimal discountedTotalAmount;

	List<ShoppingCardItemDto> shoppingCardItemDtos;



}
