package com.sri.SpringSecurity.security.jwt;

public class AuthenticateRequest {

	private String username;
	private String password;

	public AuthenticateRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthenticateRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AuthenticateRequest [username=" + username + ", password=" + password + "]";
	}

}
