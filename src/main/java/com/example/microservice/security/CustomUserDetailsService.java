package com.example.microservice.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.microservice.entity.AppUser;
import com.example.microservice.reposistry.UserRepo;


@Service
public class CustomUserDetailsService  implements UserDetailsService {
	private UserRepo userRepo;
	public CustomUserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = 	userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found:" + username));

	     return User.withUsername(user.getUsername())
	                .password(user.getPassword())
	                .roles(user.getRole())
	                .build();
	}

}
