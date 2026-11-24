package com.example.microservice.service;   // ✅ add this

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.microservice.dto.InvestorRequest;
import com.example.microservice.dto.InvestorResponse;
import com.example.microservice.entity.Investors;
import com.example.microservice.reposistry.InvestorRepo;

@Service
public class InvestorService {

    private final InvestorRepo investorRepo;

    public InvestorService(InvestorRepo investorRepo) {
        this.investorRepo = investorRepo;
    }

    public Investors saveInvestor(InvestorRequest investorRequest) {

        Investors investor = new Investors();
        investor.setCity(investorRequest.city());
        investor.setName(investorRequest.name());
        investor.setCreatedDate(LocalDateTime.now());

        return investorRepo.save(investor);
    }

    public List<Investors> getAllInvestors() {
          return investorRepo.findAll();
    }
}