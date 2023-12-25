package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByUsername(String userName);

    Customer findByEmail(String email);
}
