package com.hilmibaskoparan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shopping_cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCard extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "shoppingCard")
    List<ShoppingCardItem> shoppingCardItems;

    private BigDecimal totalAmount;

    private BigDecimal discountedTotalAmount;

    public ShoppingCard(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId());
    }
}
