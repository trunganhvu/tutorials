package org.anhvt.springbootpostgrebackend.entity.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "user_login_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;

    private String ipAddress;

    private String userAgent;

    private Date loginDate;

    @Column(length = 1000)
    private String refreshToken;

    private Date expiryDate;

    private String active;

    private String deletedFlg;
}
