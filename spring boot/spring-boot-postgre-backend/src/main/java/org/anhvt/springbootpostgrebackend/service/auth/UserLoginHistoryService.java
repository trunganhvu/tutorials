package org.anhvt.springbootpostgrebackend.service.auth;

import jakarta.transaction.Transactional;
import org.anhvt.springbootpostgrebackend.config.security.SecurityUtils;
import org.anhvt.springbootpostgrebackend.entity.auth.UserLoginHistory;
import org.anhvt.springbootpostgrebackend.enums.ActiveFlg;
import org.anhvt.springbootpostgrebackend.enums.DltFlg;
import org.anhvt.springbootpostgrebackend.exception.BusinessException;
import org.anhvt.springbootpostgrebackend.repository.auth.UserLoginHistoryRepository;
import org.anhvt.springbootpostgrebackend.utils.constant.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class UserLoginHistoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginHistoryService.class);
    @Autowired
    private UserLoginHistoryRepository userLoginHistoryRepository;
    @Value("${app.jwt.refresh.token.expiration}")
    private Long jwtRefreshTokenExpiration;

    @Transactional
    public void saveUserLoginHistory(String username, String refreshToken, String ipAddress, String userAgent) {
        LOGGER.info("saveUserLoginHistory: {} {} {}", username, ipAddress, userAgent);
        UserLoginHistory userLoginHistory = new UserLoginHistory();
        userLoginHistory.setRefreshToken(refreshToken);
        userLoginHistory.setIpAddress(ipAddress);
        userLoginHistory.setUserAgent(userAgent);
        userLoginHistory.setUsername(username);

        // Login time and expiry time
        Instant now = Instant.now();
        userLoginHistory.setLoginDate(Date.from(now));
        userLoginHistory.setExpiryDate(Date.from(now.plusSeconds(jwtRefreshTokenExpiration)));

        userLoginHistory.setActive(ActiveFlg.Y.toString());
        userLoginHistory.setDeletedFlg(ActiveFlg.N.toString());
        userLoginHistoryRepository.save(userLoginHistory);
    }

    public boolean checkTokenExist(String refreshToken) {
        LOGGER.info("checkTokenExist");
        Optional<UserLoginHistory> userLoginHistory = userLoginHistoryRepository
                .findByRefreshTokenAndDeletedFlg(refreshToken, DltFlg.N.name());
        if (userLoginHistory.isEmpty()) {
            return false;
        }
        return userLoginHistory.get().getExpiryDate().after(new Date());
    }

    @Transactional
    public void deleteUserLoginHistory(String refreshToken) {
        LOGGER.info("deleteUserLoginHistory");
        Optional<UserLoginHistory> userLoginHistory = userLoginHistoryRepository
                .findByRefreshTokenAndDeletedFlg(refreshToken, DltFlg.N.name());
        if (userLoginHistory.isEmpty()) {
            throw new BusinessException(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
        }
        userLoginHistory.get().setDeletedFlg(DltFlg.Y.name());
        userLoginHistoryRepository.save(userLoginHistory.get());
    }
}
