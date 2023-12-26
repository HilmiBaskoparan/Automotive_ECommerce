package com.hilmibaskoparan.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "shopping_card_items")
@Entity
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ShoppingCardItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCard shoppingCard;

    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;
}
