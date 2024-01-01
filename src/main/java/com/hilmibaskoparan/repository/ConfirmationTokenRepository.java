package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.EmailConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<EmailConfirmationToken, Integer> {

    Optional<EmailConfirmationToken> findByConfirmationToken(String confirmationToken);

}
