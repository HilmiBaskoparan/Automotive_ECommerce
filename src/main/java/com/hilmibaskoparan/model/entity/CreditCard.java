package com.hilmibaskoparan.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "credit_cards")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CreditCard extends BaseEntity {

    @Column(name = "card_holder_name")
    private String cardHoldername;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expire_month")
    private String expireMonth;

    @Column(name = "expire_year")
    private String expireYear;

    @Column(name = "cvc")
    private String cvc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
