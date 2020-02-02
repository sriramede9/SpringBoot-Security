package com.sri.SpringSecurity.security.withjpa;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sri.SpringSecurity.security.model.User;

public class MyUserDetails implements UserDetails {

//	private String username;

	private User user;

	private List<GrantedAuthority> authorities;

	public MyUserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyUserDetails(User user) {
		super();
		this.user = user;
	}

//	public MyUserDetails(String username) {
//		super();
//		this.username = username;
//	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub

		String[] split = user.getRoles().split(",");

		List<SimpleGrantedAuthority> collect = Arrays.stream(split).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		return collect;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
