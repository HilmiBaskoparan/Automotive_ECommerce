package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
