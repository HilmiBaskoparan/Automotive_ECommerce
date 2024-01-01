package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.RefreshTokenService;
import com.hilmibaskoparan.config.AppProperties;
import com.hilmibaskoparan.exception.TokenRefreshException;
import com.hilmibaskoparan.model.entity.RefreshToken;
import com.hilmibaskoparan.repository.RefreshTokenRepository;
import com.hilmibaskoparan.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final AppProperties appProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Autowired
    public RefreshTokenServiceImpl(AppProperties appProperties, RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.appProperties = appProperties;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Transactional
    @Override
    public int deleteByUserId(int userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {

        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(),
                    "Refresh token was expired. please make a new sign in request");

        }
        return token;
    }

    @Transactional
    @Override
    public RefreshToken createRefreshToken(int id) {

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(id).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(appProperties.getAuth().getJwtRefreshExpirationMs()));
        refreshToken.setToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(refreshToken);
    }
}
