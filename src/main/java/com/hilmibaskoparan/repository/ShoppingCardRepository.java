package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.ShoppingCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCardRepository extends JpaRepository<ShoppingCard, Integer> {

    Optional<ShoppingCard> findByCustomerId(int customerId);
}
