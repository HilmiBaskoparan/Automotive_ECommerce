package com.hilmibaskoparan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    private String bankName;
    private String creditCardNumber;
    //private User User;
    // private User user.getName() + user.getUsername()
    private Date expirationDate;
    //private int cvc;
}
