package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.model.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    Optional<RefreshToken> findByToken(String token);

    int deleteByUserId(int userId);

    RefreshToken verifyExpiration(RefreshToken token);

    RefreshToken createRefreshToken(int id);
}
