package com.hilmibaskoparan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String phone;
    private Boolean sex;
    private Date birthDate;

    @OneToOne(mappedBy = "user")
    private Basket basket;

    @OneToMany(mappedBy = "user")
    private List<CreditCard> creditCards;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<UserLog> userLogs;
}