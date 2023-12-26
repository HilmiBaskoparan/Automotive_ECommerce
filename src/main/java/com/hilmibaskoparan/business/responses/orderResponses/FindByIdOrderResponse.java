package com.hilmibaskoparan.business.responses.orderResponses;
import java.util.Date;
import java.util.List;

import com.hilmibaskoparan.business.responses.productResponses.GetAllProductResponse;
import com.hilmibaskoparan.model.entity.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindByIdOrderResponse {

	private int id;

	private final String companyName = "HILMI-AUTOMOTIVE";

	private OrderStatus orderStatus;

	private List<GetAllProductResponse> getAllProductResponses;

	private Date orderDate;

}
