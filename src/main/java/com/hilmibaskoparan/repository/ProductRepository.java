package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Set<Product> findByCategoryId(int categoryId);

    @Query(value="SELECT * FROM PRODUCTS LIMIT 4" ,nativeQuery = true)
    List<Product> findFirstFive();

    @Query(value = "SELECT * FROM PRODUCTS ORDER BY CREATED_DATE DESC LIMIT 4",nativeQuery = true)
    List<Product> findNewFiveProducts();


    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND (COALESCE(:keyword,'') = '' OR p.name LIKE %:keyword%) " +
            "AND (:minPrice is null OR p.price >= :minPrice) AND (:maxPrice is null OR p.price <= :maxPrice) AND p.quantity > 0")
    Page<Product> findByCategoryId(@Param("keyword") String keyword, @Param("categoryId") int categoryId,
                                   @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice,
                                   Pageable pageable);


    @Query("SELECT p FROM Product p WHERE p.brand.id = :brandId AND (COALESCE(:keyword, '') = '' OR p.name LIKE %:keyword%) " +
            "AND (:minPrice is null OR p.price >= :minPrice) AND (:maxPrice is null OR p.price <= :maxPrice)  AND p.quantity > 0 ")
    Page<Product> findByBrandId(@Param("keyword") String keyword, @Param("brandId") int brandId,
                                @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice,
                                Pageable pageable);




    @Query("SELECT p FROM Product p WHERE (:keyword IS NULL OR p.name LIKE %:keyword%) AND (:minPrice is null OR p.price >= :minPrice) AND "
            + "(:maxPrice is null OR p.price <= :maxPrice)  AND p.quantity > 0")
    Page<Product> findAll(@Param("keyword") String keyword, @Param("minPrice") BigDecimal minPrice,
                          @Param("maxPrice") BigDecimal maxPrice,  Pageable pageable);
}
