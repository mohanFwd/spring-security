package com.example.microservice.service;

import org.springframework.stereotype.Service;

import com.example.microservice.dto.AccountRequest;
import com.example.microservice.entity.Accounts;
import com.example.microservice.reposistry.AccountsRepo;

@Service
public class AccountService {

private final AccountsRepo accountsRepo;

public AccountService(AccountsRepo accountsRepo) {
	super();
	this.accountsRepo = accountsRepo;
}


public Accounts saveAccount(AccountRequest  accountRequest) {

	Accounts accounts = new Accounts();
	accounts.setAccountId(accountRequest.acountId());
	accounts.setAccountType(accountRequest.accountType());
	accounts.setBalance(accountRequest.balance());
	accounts.setInvestorId(accountRequest.investorId());
	return accountsRepo.save(accounts);

}

}
