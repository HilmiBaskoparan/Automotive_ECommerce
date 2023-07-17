package com.hilmibaskoparan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "basket")
public class BasketEntity extends BaseEntity {

    private String productName;
    private int amount;
    private double price;
    //private User User;
}
