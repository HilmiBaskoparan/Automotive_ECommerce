package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.OrderItemService;
import com.hilmibaskoparan.model.entity.OrderItem;
import com.hilmibaskoparan.repository.OrderItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    @Override
    public List<OrderItem> addOrderItem(List<OrderItem> orderItems) {
        return orderItemRepository.saveAll(orderItems);
    }

    @Override
    public List<Integer> findTopFiveProductIds() {
        return orderItemRepository.findTopFiveProductIds();
    }

    @Override
    public List<Integer> findNewOrderFiveProductIds() {
        return orderItemRepository.findNewFiveProductIds();
    }
}
