package com.sri.SpringSecurity.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class ConfigJbdcSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// TODO Auto-generated method stub
		
		
		
		auth
		.jdbcAuthentication()
		.dataSource(dataSource)
		.withDefaultSchema()
		.withUser(User.withUsername("user").password("pass").roles("USER"))
		.withUser(User.withUsername("admin").password("admin").roles("USER","ADMIN"));
	
		
		
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.
		authorizeRequests()
		.antMatchers("/hi","/admin").hasRole("ADMIN") //most restrictive
		.antMatchers("/hi","/user").hasRole("USER") //less restrictive
		.antMatchers("/h2-console").permitAll()
		.and()
		.formLogin();
	}

	
}
