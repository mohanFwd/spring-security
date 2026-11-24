package com.example.microservice.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.microservice.dto.AuthResponse;
import com.example.microservice.dto.LoginRequest;
import com.example.microservice.dto.RegisterRequest;
import com.example.microservice.entity.AppUser;
import com.example.microservice.reposistry.UserRepo;
import com.example.microservice.security.JwtService;

@Service
public class AuthService {


	private final UserRepo userRepo;

	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;

	private final PasswordEncoder passwordEncoder;

	public AuthService(UserRepo userRepo, JwtService jwtService, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
	}

//Register
	public void register(RegisterRequest register) {

	    if (userRepo.existsByUsername(register.username())) {
	        throw new IllegalArgumentException("Username already exists: " + register.username());
	    }

	    AppUser app = new AppUser();
	    app.setUsername(register.username());
	    app.setPassword(passwordEncoder.encode(register.password()));
	    app.setRole(register.role());
	    app.setCreatedDate(LocalDateTime.now());
	    userRepo.save(app);
	}

	   /** Checks username + password and returns a JWT. Throws BadCredentialsException if wrong. */
    public AuthResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(user);
        return new AuthResponse(token, "Bearer", jwtService.getExpirationSeconds(), user.getUsername());
    }

    public static class UsernameTakenException extends RuntimeException {
        public UsernameTakenException(String username) {
            super("Username already exists: " + username);
        }
    }

}
