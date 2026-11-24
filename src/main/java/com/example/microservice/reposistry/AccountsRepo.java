package com.example.microservice.reposistry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservice.entity.Accounts;

public interface AccountsRepo extends JpaRepository<Accounts ,String>{

}
