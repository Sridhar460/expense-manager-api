package com.example.expense.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense.customuserdetails.CustomUserDetailsService;
import com.example.expense.entity.JwtResponse;
import com.example.expense.entity.LoginModel;
import com.example.expense.service.UserService;
import com.example.expense.user.User;
import com.example.expense.user.UserModel;
import com.example.expense.util.JwtTokenUtil;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authentcationManager;
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private CustomUserDetailsService userDetailService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginModel loginModel) throws Exception{
		
		authenticate(loginModel.getEmail(), loginModel.getPassword());
		
		final UserDetails userDetails = userDetailService.loadUserByUsername(loginModel.getEmail());
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		
	
		return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
	}
	private void authenticate(String email, String password) throws Exception{
		
		try {
			
			 authentcationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			
		} catch (DisabledException e) {
			throw new Exception("User Disabled");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad Credentials");
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> save(@Valid @RequestBody UserModel user){
		
		return new ResponseEntity<User>(userService.createuser(user), HttpStatus.CREATED);
		
	}
}
