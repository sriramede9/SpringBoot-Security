//package com.sri.SpringSecurity.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User.UserBuilder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
////@EnableWebSecurity
////@Configuration
//public class ConfigSecurity extends WebSecurityConfigurerAdapter {
//
////	@Override
////	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.inMemoryAuthentication().withUser("Sri").password("sri").roles("USER").and().withUser("Sri ram").password("sri").roles("ADMIN");
////
////	}
////
////	@Bean
////	public PasswordEncoder getPasswordEncoder() {
////		return NoOpPasswordEncoder.getInstance();
////	}
//	
//	
//	//second approach as per documentation
//	
//	@Bean
//	public UserDetailsService userDetailsService()  {
//	    // ensure the passwords are encoded properly
//	    UserBuilder users = User.withDefaultPasswordEncoder();
//	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//	    manager.createUser(users.username("user").password("password").roles("USER").build());
//	    manager.createUser(users.username("admin").password("password").roles("USER","ADMIN").build());
//	    return manager;
//	}
//
//	
//	
//	//now use HttpSecurity to filter the requests based on roles
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
////		http.
////		authorizeRequests()
////		.antMatchers("/**")  //all incoming requests
////		.hasAnyRole("ADMIN") //only admin can request
////		.and()
////		.formLogin();        //and should login using form
//		
//		http.
//		authorizeRequests()
//		.antMatchers("/hi","/admin").hasRole("ADMIN") //most restrictive
//		.antMatchers("/hi","/user").hasRole("USER") //less restrictive
//		.antMatchers("/").permitAll()
//		.and()
//		.formLogin();
//	}
//	
//
//}
