package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.CouponService;
import com.hilmibaskoparan.business.abstracts.OrderItemService;
import com.hilmibaskoparan.business.abstracts.OrderService;
import com.hilmibaskoparan.business.abstracts.ShoppingCardService;
import com.hilmibaskoparan.business.requests.orderRequest.CreateOrderRequest;
import com.hilmibaskoparan.business.responses.orderResponses.FindByCustomerIdOrderResponse;
import com.hilmibaskoparan.business.responses.orderResponses.FindByIdOrderResponse;
import com.hilmibaskoparan.business.responses.productResponses.GetAllProductResponse;
import com.hilmibaskoparan.core.mappers.ModelMapperService;
import com.hilmibaskoparan.model.entity.*;
import com.hilmibaskoparan.repository.OrderRepository;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ShoppingCardService shoppingCardService;
    private final ModelMapperService modelMapperService;
    private final OrderItemService orderItemService;
    private final CouponService couponService;

    public OrderServiceImpl(OrderRepository orderRepository, ShoppingCardService shoppingCardService, ModelMapperService modelMapperService, OrderItemService orderItemService, CouponService couponService) {
        this.orderRepository = orderRepository;
        this.shoppingCardService = shoppingCardService;
        this.modelMapperService = modelMapperService;
        this.orderItemService = orderItemService;
        this.couponService = couponService;
    }

    @Override
    public int createOrder(CreateOrderRequest createOrderRequest, String discountCode) {

        ShoppingCard shoppingCard = shoppingCardService.findById(createOrderRequest.getShoppingCardId());
        List<ShoppingCardItem> shoppingCardItems = shoppingCard.getShoppingCardItems();

        List<OrderItem> orderItems = new ArrayList<>();
        Customer customer = shoppingCard.getCustomer();

        Order order = Order.builder().customer(customer).orderItems(orderItems).orderStatus(OrderStatus.PREPARING)
                .totalAmount(shoppingCard.getTotalAmount()).orderDate(DateUtils.createNow().getTime()).build();

        if (discountCode != null) {
            order.setCoupon(couponService.findByCode(discountCode));
        }
        orderRepository.save(order);
        for (ShoppingCardItem shoppingCardItem : shoppingCardItems) {
            orderItems.add(OrderItem.builder().product(shoppingCardItem.getProduct()).order(order)
                    .quantity(shoppingCardItem.getQuantity()).price(shoppingCardItem.getProduct().getPrice()
                            .multiply(BigDecimal.valueOf(shoppingCardItem.getQuantity())))
                    .build());

        }

        orderItemService.addOrderItem(orderItems);
        orderRepository.save(order);

        return order.getId();
    }

    @Override
    public List<FindByCustomerIdOrderResponse> findByCustomerId(int customerId) {

        List<Order> orders = this.orderRepository.findByCustomerId(customerId);

        List<FindByCustomerIdOrderResponse> responses = orders.stream()
                .map(order -> modelMapperService.forResponse().map(order, FindByCustomerIdOrderResponse.class))
                .collect(Collectors.toList());

        responses.stream().forEach(response -> {

            Order order = orders.stream().filter(o -> o.getId() == response.getId()).findFirst().orElse(null);

            if (order != null && order.getCoupon() != null) {
                BigDecimal discountTotalAmount = response.getTotalAmount().subtract(response.getTotalAmount()
                        .multiply(order.getCoupon().getDiscount()).divide(BigDecimal.valueOf(100)));

                response.setDiscountTotalAmount(discountTotalAmount);
            } else {
                response.setDiscountTotalAmount(response.getTotalAmount());
            }

        });

        return responses;
    }

    @Override
    public FindByIdOrderResponse findById(int id) {

        Order order = orderRepository.findById(id).get();

        List<Product> products = order.getOrderItems().stream().map(item -> item.getProduct())
                .collect(Collectors.toList());

        List<GetAllProductResponse> getAllProductResponse = products.stream()
                .map(product -> modelMapperService.forResponse().map(product, GetAllProductResponse.class))
                .collect(Collectors.toList());

        FindByIdOrderResponse responses = modelMapperService.forResponse().map(order, FindByIdOrderResponse.class);
        responses.setGetAllProductResponses(getAllProductResponse);

        return responses;
    }
}
