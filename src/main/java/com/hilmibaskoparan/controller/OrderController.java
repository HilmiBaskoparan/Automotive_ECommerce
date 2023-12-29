package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.business.abstracts.OrderService;
import com.hilmibaskoparan.business.requests.orderRequest.CreateOrderRequest;
import com.hilmibaskoparan.business.responses.orderResponses.FindByCustomerIdOrderResponse;
import com.hilmibaskoparan.business.responses.orderResponses.FindByIdOrderResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/orders/findByCustomerId")
    public List<FindByCustomerIdOrderResponse> findByCustomerId(@RequestParam int customerId){
        return orderService.findByCustomerId(customerId);
    }

    @GetMapping("/order")
    public FindByIdOrderResponse findById(@RequestParam int id){
        return orderService.findById(id);
    }

}
