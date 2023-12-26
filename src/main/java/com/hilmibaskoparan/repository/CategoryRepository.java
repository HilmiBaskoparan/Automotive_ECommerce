package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
