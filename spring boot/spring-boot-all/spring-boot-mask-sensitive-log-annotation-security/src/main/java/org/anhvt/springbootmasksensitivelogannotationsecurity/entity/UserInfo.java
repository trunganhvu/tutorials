package org.anhvt.springbootmasksensitivelogannotationsecurity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.anhvt.springbootmasksensitivelogannotationsecurity.annotation.MaskingData;

@Entity
@Table(name = "user_details")
@Getter
@Setter
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@MaskingData
	private String email;

	@MaskingData
	private String password;

	private String roles;

	@MaskingData
	@Column(name = "card_no")
	private String cardNo;

	public UserInfo() {
	}

	public UserInfo(int id, String name, String email, String password, String roles, String cardNo) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.cardNo = cardNo;
	}
}
