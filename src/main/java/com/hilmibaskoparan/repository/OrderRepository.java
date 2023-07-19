package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
