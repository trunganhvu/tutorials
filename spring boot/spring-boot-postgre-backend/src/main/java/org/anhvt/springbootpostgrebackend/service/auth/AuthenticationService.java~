package org.anhvt.springbootpostgrebackend.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.lang.Strings;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.anhvt.springbootpostgrebackend.config.security.SecurityUtils;
import org.anhvt.springbootpostgrebackend.config.security.TokenProvider;
import org.anhvt.springbootpostgrebackend.exception.BusinessException;
import org.anhvt.springbootpostgrebackend.payload.request.auth.LogoutRequest;
import org.anhvt.springbootpostgrebackend.payload.request.auth.RefreshTokenRequest;
import org.anhvt.springbootpostgrebackend.payload.response.auth.AuthResponse;
import org.anhvt.springbootpostgrebackend.queue.pub.RedisMessagePublisher;
import org.anhvt.springbootpostgrebackend.utils.constant.ResponseCode;
import org.anhvt.springbootpostgrebackend.utils.http.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserLoginHistoryService userLoginHistoryService;
    @Autowired
    private RedisMessagePublisher redisPublisher;

    @Transactional
    public AuthResponse authenticate(String username, String password, HttpServletRequest request) {
        // Authenticate and generate token
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String refreshToken = tokenProvider.generateRefreshToken(authentication);
        String accessToken = tokenProvider.generateAccessToken(authentication, getTopCharRefreshToken(refreshToken));

        // Get IP Address and User-Agent
        Map<String, String> requestInfo = RequestUtils.getRequestInfo(request);
        String ipAddress = refreshToken.contains("ipAddress") ? requestInfo.get("ipAddress") : Strings.EMPTY;
        String userAgent = refreshToken.contains("userAgent") ? requestInfo.get("userAgent") : Strings.EMPTY;
        userLoginHistoryService.saveUserLoginHistory(username, refreshToken, ipAddress, userAgent);

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        UsernamePasswordAuthenticationToken authentication = null;
        // Validate the refresh token and check history
        Optional<Jws<Claims>> jws = tokenProvider.validateTokenAndGetJws(refreshTokenRequest.getRefreshToken());
        if (jws.isEmpty() || !userLoginHistoryService.checkTokenExist(refreshTokenRequest.getRefreshToken())) {
            throw new BusinessException(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getMessage());
        }

        String username = jws.get().getPayload().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // Create new token
        String accessToken = tokenProvider.generateAccessToken(authentication,
                getTopCharRefreshToken(refreshTokenRequest.getRefreshToken()));

        return new AuthResponse(accessToken, refreshTokenRequest.getRefreshToken());
    }

    @Transactional
    public void logout(HttpServletRequest request, LogoutRequest logoutRequest) {
        String username = SecurityUtils.getCurrentUserLogin()
                .orElseThrow(() -> new BusinessException(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getMessage()));

        // Delete user login history
        userLoginHistoryService.deleteUserLoginHistory(logoutRequest.getRefreshToken());

        // Add to blacklist
        redisPublisher.publishSessionBlacklist(username + "|" + this.getTopCharRefreshToken(logoutRequest.getRefreshToken()));

        // Invalidate session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
    }

    private String getTopCharRefreshToken(String refreshToken) {
        return refreshToken.substring(0, 6);
    }
}
