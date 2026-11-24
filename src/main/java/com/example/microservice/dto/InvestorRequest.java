package com.example.microservice.dto;

import jakarta.validation.constraints.NotBlank;

public record InvestorRequest(

		@NotBlank(message = "Name is required")String name,
		@NotBlank(message = "City is required")String city) {

}
