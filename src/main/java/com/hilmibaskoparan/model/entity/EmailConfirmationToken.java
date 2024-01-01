package com.hilmibaskoparan.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "email_confirmation_tokens")
@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class EmailConfirmationToken extends BaseEntity {

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" ,referencedColumnName = "id")
    public User user;
}
