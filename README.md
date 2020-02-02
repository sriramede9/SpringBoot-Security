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
 
 ### Using AntMatchers to filter access based on roles
 
 ```
 http.
		authorizeRequests()
		.antMatchers("/**")  //all incoming requests
		.hasAnyRole("ADMIN") //only admin can request
		.and()
		.formLogin();        //and should login using form
 ```
http.
		authorizeRequests()
		.antMatchers("/hi","/admin").hasRole("ADMIN") //most restrictive
		.antMatchers("/hi","/user").hasRole("USER") //less restrictive
		.antMatchers("/").permitAll()
		.and()
		.formLogin();

### Using JDBC default Schema

```
auth
		.jdbcAuthentication()
		.dataSource(dataSource)
		.withDefaultSchema()
		.withUser(User.withUsername("user").password("pass").roles("USER"))
		.withUser(User.withUsername("admin").password("admin").roles("USER","ADMIN"));
```

### Using JPA 

* step one
* Make sure you have UserDetailsService(as authentication Manger expects UserDetailsService __ which has embeded UserDetails Service 
```
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
```
* Step two

Implement UserDetailsService and return it as this is the main 

```
@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User findByUsername = userRepository.findByUsername(username);

		if (findByUsername != null) {
			return new MyUserDetails(findByUsername);
		} else
			throw new UsernameNotFoundException("no such user");
	}

}

```

* Step Three
* find a user with username and enclose in MyUserDetails to return UserDetailsService obj
```
public class MyUserDetails implements UserDetails
	private User user;

	private List<GrantedAuthority> authorities;
	public MyUserDetails(User user) {
		super();
		this.user = user;
	}
```
