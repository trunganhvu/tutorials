package org.anhvt.springbootmasksensitivelogannotationsecurity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

	private String loginId;
	private String password;

}
