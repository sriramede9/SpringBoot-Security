package com.sri.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sri.SpringSecurity.security.jwt.AuthenticateRequest;
import com.sri.SpringSecurity.security.jwt.Jwtutil;
import com.sri.SpringSecurity.service.MyUserDetailsServiceJWT;

@RestController

public class JwtController {

	@Autowired
	MyUserDetailsServiceJWT myUserDetailsServiceJWT;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	Jwtutil jwtutil;

	@GetMapping("/hii")
	public String sayHello() {
		return "<h2>Hey Sri!!!</h2>";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity createAuthenticationToken(@RequestBody AuthenticateRequest request) throws Exception {

		try {
			System.out.println("Here is the request value" + request);

			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			System.out.println("Here is our authenticate value" + authenticate);

		} catch (Exception e) {
			throw new Exception("No such user with given details");
		}

		UserDetails userDetails = myUserDetailsServiceJWT.loadUserByUsername(request.getUsername());

		String generateToken = jwtutil.generateToken(userDetails);

		return ResponseEntity.ok(generateToken);

	}
}
