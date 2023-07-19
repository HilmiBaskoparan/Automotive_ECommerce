package com.hilmibaskoparan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    @Column(length = 30 , nullable = false)
    private String firstName;

    @Column(length = 30 , nullable = false)
    private String lastName;

    @Column(length = 30 , nullable = false)
    private String username;

    @Column(length = 80, nullable = false, unique = true)
    private String email;
    private String password;

    @Column(unique = true)
    @Size(min = 10, max = 10)
    private String phone;
    private Boolean sex;
    private LocalDate birthDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Basket basket;

    @OneToMany(mappedBy = "user")
    private List<CreditCard> creditCards;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<UserLog> userLogs;

    // createdTime
    // updatedTime
}
