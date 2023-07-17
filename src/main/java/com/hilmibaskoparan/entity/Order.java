package com.hilmibaskoparan.entity;

import jakarta.persistence.*;
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
public class Order extends BaseEntity {

    private String productName;
    private int amount;
    private double price;
    private String address;
    private String cargoName;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
