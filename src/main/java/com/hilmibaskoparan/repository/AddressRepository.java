package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByUserId(int customerId);

}
