package com.hilmibaskoparan.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "user_name"),
        @UniqueConstraint(columnNames = "email") })
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "failed_login_attempts")
    private int failedLoginAttempts;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @OneToMany(mappedBy = "user")
    private List<CreditCard> creditCards;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

}
