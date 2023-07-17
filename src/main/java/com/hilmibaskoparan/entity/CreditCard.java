package com.hilmibaskoparan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "credit_cards")
public class CreditCard extends BaseEntity {

    @Column(nullable = false)
    private String cardName;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
