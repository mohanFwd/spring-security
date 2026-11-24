package com.example.microservice.reposistry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservice.entity.Investors;

public interface InvestorRepo extends JpaRepository<Investors , Integer>{

}
