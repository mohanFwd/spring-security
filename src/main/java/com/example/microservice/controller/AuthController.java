package com.example.microservice.controller;

import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.dto.AuthResponse;
import com.example.microservice.dto.LoginRequest;
import com.example.microservice.dto.RegisterRequest;
import com.example.microservice.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


	private final AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

    @PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request){

    	authService.register(request);


		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "User registered"));

	}


    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    /** Needs a valid token. Shows who you are logged in as. */
    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal UserDetails user) {
        return Map.of("username", user.getUsername(), "roles", user.getAuthorities().toString());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> badLogin(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid username or password"));
    }

    @ExceptionHandler(AuthService.UsernameTakenException.class)
    public ResponseEntity<Map<String, String>> taken(AuthService.UsernameTakenException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
    }


}
