package com.example.microservice.dto;

public record AuthResponse(String token, String tokenType, long expiresInSeconds, String username) {
}
