package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.model.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    public List<OrderItem> addOrderItem(List<OrderItem> orderItems);

    public List<Integer> findTopFiveProductIds();

    public List<Integer> findNewOrderFiveProductIds();

}
