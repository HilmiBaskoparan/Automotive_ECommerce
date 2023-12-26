package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.requests.orderRequest.CreateOrderRequest;
import com.hilmibaskoparan.business.responses.orderResponses.FindByCustomerIdOrderResponse;
import com.hilmibaskoparan.business.responses.orderResponses.FindByIdOrderResponse;

import java.util.List;

public interface OrderService {

    public int createOrder(CreateOrderRequest createOrderRequest, String discountCode);

    public List<FindByCustomerIdOrderResponse> findByCustomerId(int customerId);

    public FindByIdOrderResponse findById(int id);

}
