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
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    private String productName;
    private int amount;
    private double price;
    private String address;
    private String cargoName;
    //private CreditCard CreditCard;
    //private User User;
}
