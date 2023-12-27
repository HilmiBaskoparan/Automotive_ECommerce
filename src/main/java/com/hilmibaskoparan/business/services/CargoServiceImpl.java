package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.CargoService;
import com.hilmibaskoparan.business.abstracts.OrderService;
import com.hilmibaskoparan.business.abstracts.ShoppingCardService;
import com.hilmibaskoparan.core.mappers.ModelMapperService;
import com.hilmibaskoparan.model.entity.Cargo;
import com.hilmibaskoparan.model.entity.CargoStatus;
import com.hilmibaskoparan.model.entity.Order;
import com.hilmibaskoparan.model.entity.ShoppingCard;
import com.hilmibaskoparan.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;
    private final ShoppingCardService shoppingCardService;
    private final OrderService orderService;
    private final ModelMapperService modelMapperService;

    public CargoServiceImpl(CargoRepository cargoRepository, ShoppingCardService shoppingCardService, OrderService orderService, ModelMapperService modelMapperService) {
        this.cargoRepository = cargoRepository;
        this.shoppingCardService = shoppingCardService;
        this.orderService = orderService;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public void createCargo(int shoppingCartId, int orderId) {

        ShoppingCard shoppingCard = shoppingCardService.findById(shoppingCartId);
        Order order = modelMapperService.forRequest().map(orderService.findById(orderId), Order.class);

        Cargo cargo = new Cargo();

        cargo.setCargoStatus(CargoStatus.PREPARING);
        cargo.setOrder(order);
        cargo.setCustomer(shoppingCard.getCustomer());
        cargo.setDeliveryDate(new Date());
        cargo.setShippingTrackingNo(UUID.randomUUID().toString());

        cargoRepository.save(cargo);

    }
}
