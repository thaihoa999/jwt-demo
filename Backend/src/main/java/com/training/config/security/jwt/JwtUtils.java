package com.training.config.security.jwt;

import com.training.service.impl.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger LOGGER = Logger.getLogger(JwtUtils.class);

    @Value("${app.jwt.secret.key}")
    private String secretKey;

    @Value("${app.jwt.expire.time}")
    private int expireTimeMillis;

    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Date dateNow = new Date();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(dateNow)
                .setExpiration(new Date(dateNow.getTime() + expireTimeMillis))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            LOGGER.error("Invalid JWT signature: {}", e);
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JWT token: {}", e);
        } catch (ExpiredJwtException e) {
            LOGGER.error("JWT token is expired: {}", e);
        } catch (UnsupportedJwtException e) {
            LOGGER.error("JWT token is unsupported: {}", e);
        } catch (IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty: {}", e);
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
