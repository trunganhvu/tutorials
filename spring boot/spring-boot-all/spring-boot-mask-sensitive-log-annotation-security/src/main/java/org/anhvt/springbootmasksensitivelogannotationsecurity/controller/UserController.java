package org.anhvt.springbootmasksensitivelogannotationsecurity.controller;

import org.anhvt.springbootmasksensitivelogannotationsecurity.dto.AuthRequest;
import org.anhvt.springbootmasksensitivelogannotationsecurity.entity.UserInfo;
import org.anhvt.springbootmasksensitivelogannotationsecurity.service.JwtService;
import org.anhvt.springbootmasksensitivelogannotationsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/isRunning")
	public String isRunning() {
		return "Service is running";
	}

	@PostMapping("/addUser")
	public String addUser(@RequestBody UserInfo userInfo) {
		return service.addUser(userInfo);
	}

	@GetMapping("/getAllUsers")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<UserInfo> getAllUsers() {
		return service.getAllUsers();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public UserInfo getUserById(@PathVariable int id) {
		return service.getUser(id);
	}

	@PostMapping("/getToken")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());
		}
		
		throw new UsernameNotFoundException("invalid user details.");
	}
}
