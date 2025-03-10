package org.anhvt.springbootpostgrebackend.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.anhvt.springbootpostgrebackend.config.security.TokenProvider;
import org.anhvt.springbootpostgrebackend.exception.BusinessException;
import org.anhvt.springbootpostgrebackend.utils.constant.RedisKey;
import org.anhvt.springbootpostgrebackend.utils.constant.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationFilter.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            LOGGER.info("TokenAuthenticationFilter Start");
            getJwtFromRequest(request)
                    .flatMap(tokenProvider::validateTokenAndGetJws)
                    .ifPresent(jws -> {
                        String username = jws.getPayload().getSubject();
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        // Check is logout user in blacklist
                        String key = RedisKey.SESSION_BLACKLIST + username;
                        String refreshTokenTopChar = (String) jws.getPayload().get("refresh_token_top_char");
                        if (Objects.requireNonNull(redisTemplate.opsForSet().members(key)).contains(refreshTokenTopChar)) {
                            LOGGER.info("Logout user in blacklist: {}", username);
                            throw new BusinessException(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getMessage());
                        }
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                                null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    });
        } catch (BusinessException ex) {
            LOGGER.info("Cannot set user authentication: {}", ex.getMessage());
        } catch (Exception e) {
            LOGGER.info("Cannot set user authentication with error: {}", e.getMessage());
        }
        chain.doFilter(request, response);
    }

    private Optional<String> getJwtFromRequest(HttpServletRequest request) {
        String tokenHeader = request.getHeader(TOKEN_HEADER);
        if (StringUtils.hasText(tokenHeader) && tokenHeader.startsWith(TOKEN_PREFIX)) {
            return Optional.of(tokenHeader.replace(TOKEN_PREFIX, ""));
        }
        return Optional.empty();
    }

}

