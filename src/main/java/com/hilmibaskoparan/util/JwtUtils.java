package com.hilmibaskoparan.util;

import com.hilmibaskoparan.config.AppProperties;
import com.hilmibaskoparan.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    private final AppProperties appProperties;

    public JwtUtils(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String generateJwtToken(UserDetailsImpl userDetailsImpl) {
        return generateTokenFromEmail(userDetailsImpl.getEmail());
    }

    public String generateTokenFromEmail(String email) {
        return Jwts.builder().setSubject(email).setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + appProperties.getAuth().getJwtExpirationMs()))
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getJwtSecret())

                .compact();
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(appProperties.getAuth().getJwtSecret()).parseClaimsJws(token).getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getJwtSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
