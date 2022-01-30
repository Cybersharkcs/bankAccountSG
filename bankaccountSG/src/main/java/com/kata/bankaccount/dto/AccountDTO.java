package com.kata.bankaccount.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTO implements Serializable{
	
	private long balance;
	
    private List<TransactionDTO> listTransaction;

    private CustomerDTO customer;
}
