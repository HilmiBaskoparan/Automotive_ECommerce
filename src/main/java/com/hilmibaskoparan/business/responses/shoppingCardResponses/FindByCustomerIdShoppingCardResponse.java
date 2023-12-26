package com.hilmibaskoparan.business.responses.shoppingCardResponses;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindByCustomerIdShoppingCardResponse {
	

	private int id;
	private BigDecimal totalAmount;
	private BigDecimal discountedTotalAmount;

	List<ShoppingCardItemDto> shoppingCardItemDtos;


}
