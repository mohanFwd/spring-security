package com.example.microservice.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class Accounts {

	@Id
	@Column(name = "account_id")
	private String accountId;


	@Column(name = "investor_id")
	private int investorId;

	@Column(name= "account_type")
	private String accountType;


	@Column(name = "balance" ,precision = 19, scale = 2)
	private BigDecimal balance;


	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	public int getInvestorId() {
		return investorId;
	}


	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
