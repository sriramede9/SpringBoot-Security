package com.sri.SpringSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class ConfigJPASecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailservice;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// checking userDetailsService

		auth.userDetailsService(userDetailservice); 
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/hi", "/admin").hasRole("ADMIN") // most restrictive
				.antMatchers("/hi", "/user").hasRole("USER") // less restrictive
				.antMatchers("/").permitAll().and().formLogin();
	}

}
