package org.anhvt.springbootpostgrebackend.repository.auth;

import org.anhvt.springbootpostgrebackend.entity.auth.UserLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoginHistoryRepository extends JpaRepository<UserLoginHistory, String> {
    Optional<UserLoginHistory> findByRefreshTokenAndDeletedFlg(String refreshToken, String deletedFlg);
}
