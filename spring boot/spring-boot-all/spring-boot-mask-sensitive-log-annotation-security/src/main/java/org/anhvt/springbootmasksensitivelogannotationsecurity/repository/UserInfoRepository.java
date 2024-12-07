package org.anhvt.springbootmasksensitivelogannotationsecurity.repository;

import org.anhvt.springbootmasksensitivelogannotationsecurity.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	Optional<UserInfo> findByName(String username);
}
