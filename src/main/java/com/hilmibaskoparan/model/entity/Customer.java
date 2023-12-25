package com.hilmibaskoparan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "customers")
@Entity
@Data
@AllArgsConstructor
@PrimaryKeyJoinColumn(name="id")
@NoArgsConstructor
public class Customer extends User {

    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
    private List<Order> orders;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "customer")
    private ShoppingCard shoppingCard;

    public Customer(String firstName, String lastName, String username, String password, String email) {
        super(username,password,email);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
