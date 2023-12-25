package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

    List<CreditCard> findByUserId(int userId);
}
