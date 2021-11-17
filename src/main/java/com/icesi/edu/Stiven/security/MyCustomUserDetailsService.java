package com.icesi.edu.Stiven.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.repositories.UserRepository;
import com.icesi.edu.Stiven.model.person.UserModel;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {

		UserModel us = userRepo.findByUserName(nickname);

		if (us != null) {

			User.UserBuilder builder = User.withUsername(nickname).password(us.getPassword())
					.roles(us.getType().toString());

			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}