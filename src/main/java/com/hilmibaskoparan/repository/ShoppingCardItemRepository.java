package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.ShoppingCardItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCardItemRepository extends JpaRepository<ShoppingCardItem, Integer> {

    List<ShoppingCardItem> findByShoppingCardCustomerId(int customerId);
}
