//package com.sri.SpringSecurity.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class ConfigLDAPSecurity extends WebSecurityConfigurerAdapter {
//
//	
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		 auth
//	      .ldapAuthentication()
//	        .userDnPatterns("uid={0},ou=people") //dn--> distinguishname
//	        .groupSearchBase("ou=groups")
//	        .contextSource()
//	          .url("ldap://localhost:8389/dc=springframework,dc=org")
//	          .and()
//	        .passwordCompare()
//	          .passwordEncoder(new LdapShaPasswordEncoder())
//	          .passwordAttribute("userPassword");
//	}
////			Sample ldif data
////	dn: ou=people,dc=springframework,dc=org
////			objectclass: top
////			objectclass: organizationalUnit
////			ou: people
//	
////	dn: uid=bob,ou=people,dc=springframework,dc=org
////			objectclass: top
////			objectclass: person
////			objectclass: organizationalPerson
////			objectclass: inetOrgPerson
////			cn: Bob Hamilton
////			sn: Hamilton
////			uid: bob
////			userPassword: bobspassword
//
////	@Bean
////	public PasswordEncoder getPasswordEncoder() {
////		return NoOpPasswordEncoder.getInstance();
////	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.authorizeRequests().antMatchers("/hi", "/admin").hasRole("ADMIN") // most restrictive
//				.antMatchers("/hi", "/user").hasRole("USER") // less restrictive
//				.antMatchers("/h2-console").permitAll().and().formLogin();
//	}
//}
