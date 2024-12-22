package org.anhvt.springbootpostgrebackend.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.anhvt.springbootpostgrebackend.model.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenProvider.class);

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.access.token.expiration}")
    private Long jwtAccessTokenExpiration;

    @Value("${app.jwt.refresh.token.expiration}")
    private Long jwtRefreshTokenExpiration;

    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "spring-boot-postgre-backend";
    public static final String TOKEN_AUDIENCE = "frontend";

    public String generateAccessToken(Authentication authentication, String refreshTokenTopChar) {
        LOGGER.info("generateAccessToken start");
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        byte[] signingKey = jwtSecret.getBytes();

        Instant now = Instant.now();

        return Jwts.builder()
                .header().add("typ", TOKEN_TYPE)
                .and()
                .signWith(Keys.hmacShaKeyFor(signingKey), Jwts.SIG.HS512)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(jwtAccessTokenExpiration)))
                .id(UUID.randomUUID().toString())
                .issuer(TOKEN_ISSUER)
                .audience().add(TOKEN_AUDIENCE)
                .and()
                .subject(user.getUsername())
                .claim("rol", roles)
                .claim("name", user.getName())
                .claim("preferred_username", user.getUsername())
                .claim("email", user.getEmail())
                .claim("refresh_token_top_char", refreshTokenTopChar)
                .compact();
    }

    public String generateRefreshToken(Authentication authentication) {
        LOGGER.info("generateRefreshToken start");
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        byte[] signingKey = jwtSecret.getBytes();

        Instant now = Instant.now();

        return Jwts.builder()
                .header().add("typ", TOKEN_TYPE)
                .and()
                .signWith(Keys.hmacShaKeyFor(signingKey), Jwts.SIG.HS512)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(jwtRefreshTokenExpiration)))
                .id(UUID.randomUUID().toString())
                .issuer(TOKEN_ISSUER)
                .audience().add(TOKEN_AUDIENCE)
                .and()
                .subject(user.getUsername())
                .claim("email", user.getEmail())
                .compact();
    }

    public Optional<Jws<Claims>> validateTokenAndGetJws(String token) {
        try {
            LOGGER.info("validateTokenAndGetJws start");
            byte[] signingKey = jwtSecret.getBytes();

            Jws<Claims> jws = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(signingKey))
                    .build()
                    .parseSignedClaims(token);

            return Optional.of(jws);
        } catch (ExpiredJwtException exception) {
            LOGGER.error("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            LOGGER.error("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
        } catch (MalformedJwtException exception) {
            LOGGER.error("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
        } catch (SignatureException exception) {
            LOGGER.error("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
        } catch (IllegalArgumentException exception) {
            LOGGER.error("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
        }
        return Optional.empty();
    }

}
