package com.example.microservice.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record AccountRequest(

		@NotBlank(message = "accountId is required") String acountId,
		 int investorId,
		@NotBlank(message = "accountType is required") String accountType,
		@NotNull(message = "Balance is required")
		@PositiveOrZero(message = "Balance cannot be negative")
		BigDecimal balance ) {

}
