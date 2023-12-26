package com.hilmibaskoparan.business.responses.orderResponses;

import java.math.BigDecimal;
import java.util.Date;

import com.hilmibaskoparan.model.entity.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindByCustomerIdOrderResponse {

	private int id;
	
	private final String companyName = "HILMI E-COMMERCE";
	
	private OrderStatus orderStatus;
	
	private BigDecimal totalAmount;
	
	private BigDecimal discountTotalAmount;
	
	private Date orderDate;
	
	
}
 