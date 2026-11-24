
package com.example.microservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.microservice.dto.AccountRequest;
import com.example.microservice.dto.InvestorRequest;
import com.example.microservice.dto.InvestorResponse;
import com.example.microservice.entity.Accounts;
import com.example.microservice.entity.Investors;
import com.example.microservice.service.AccountService;
import com.example.microservice.service.InvestorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final InvestorService investorService;
    private final AccountService accountService;

    public AdminController(InvestorService investorService ,AccountService accountService) {
        this.investorService = investorService;
        this.accountService =accountService;
    }

    @GetMapping(value = "/dashboard", produces = MediaType.TEXT_HTML_VALUE)
    public String loginDashBoard() {
        return "<h1>Welcome to Login Dashboard</h1>";
    }

    @PostMapping("/investor")
    public ResponseEntity<Map<String, String>> createInvestor(@Valid @RequestBody InvestorRequest invRequest) {

        Investors investor = investorService.saveInvestor(invRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                        "message", "Investor created successfully",
                        "investorId", String.valueOf(investor.getInvestorId())
                ));
    }


    @GetMapping("/investors")
    public List<InvestorResponse> getAllInvestors() {
        return investorService.getAllInvestors()
                .stream()
                .map(inv -> new InvestorResponse(inv.getName(), inv.getCity(), String.valueOf( inv.getInvestorId())))
                .toList();
    }



     @PostMapping("/account")
    public ResponseEntity<Map<String, String>> createAccount(@Valid @RequestBody AccountRequest accountRequest){

    	Accounts account = accountService.saveAccount(accountRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Message" , "Account created successfully",
				"accountId" , String.valueOf(account.getAccountId())));

    }
}
