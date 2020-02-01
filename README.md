# SpringBoot-Security
Following Documentation

## Spring Generated Password

```
UserName :user
Password : spring generated password
```

### to configure your own username and password for default role user

```
spring.security.user.name=foo
spring.security.user.password=bar
```
### Configure AuthenticationManagerBuilder 
 
 * username
 * password
 * role
 
 ### Two Ways to configure
 
 ```
 	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("Sri").password("sri").roles("ADMIN");

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
 ```
 
 ```
 	@Bean
	public UserDetailsService userDetailsService()  {
	    // ensure the passwords are encoded properly
	    UserBuilder users = User.withDefaultPasswordEncoder();
	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	    manager.createUser(users.username("user").password("password").roles("USER").build());
	    manager.createUser(users.username("admin").password("password").roles("USER","ADMIN").build());
	    return manager;
	}
 ```
