package com.hilmibaskoparan.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Entity
@Data
@Table(name="refresh_tokens")
@EqualsAndHashCode(callSuper = false)
public class RefreshToken extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id" ,referencedColumnName = "id")
    private User user;


    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;
}
