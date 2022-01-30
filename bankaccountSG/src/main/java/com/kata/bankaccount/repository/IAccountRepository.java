package com.kata.bankaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kata.bankaccount.entite.AccountEntity;

public interface IAccountRepository extends JpaRepository<AccountEntity, Long> {

}
