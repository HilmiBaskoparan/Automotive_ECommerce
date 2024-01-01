package com.hilmibaskoparan.repository;

import com.hilmibaskoparan.model.entity.RefreshToken;
import com.hilmibaskoparan.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);

    int deleteByUser(User user);

}
