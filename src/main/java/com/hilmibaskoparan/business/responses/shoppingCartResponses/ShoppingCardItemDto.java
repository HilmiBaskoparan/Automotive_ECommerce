package com.hilmibaskoparan.business.responses.shoppingCartResponses;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ShoppingCardItemDto {
	
	private int id;
	public String productName;
	public BigDecimal price;
	public BigDecimal totalPrice;
	public int quantity;
	
	List<String> urls;


}
