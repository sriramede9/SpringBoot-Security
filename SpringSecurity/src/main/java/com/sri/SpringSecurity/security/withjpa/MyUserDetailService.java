package com.sri.SpringSecurity.security.withjpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sri.SpringSecurity.security.model.User;
import com.sri.SpringSecurity.security.model.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// return new MyUserDetails("sri");

		// instead of giving a hard coded user Details service let's get this from a
		// repository
		User findByUsername = userRepository.findByUsername(username);
		
		System.out.println("Here is your user"+findByUsername);

		if (findByUsername != null) {
			return new MyUserDetails(findByUsername);
		} else
			throw new UsernameNotFoundException("no such user");
	}

}
