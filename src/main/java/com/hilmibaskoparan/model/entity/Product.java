package com.hilmibaskoparan.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    @EqualsAndHashCode.Include
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private int quantity;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    Set<ShoppingCardItem> shoppingCardItemSet;


    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    List<OrderItem> orderItems;
}
