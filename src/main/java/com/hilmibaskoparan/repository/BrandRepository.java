package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    List<Brand> findByCategoriesId(int categoryName);

}
