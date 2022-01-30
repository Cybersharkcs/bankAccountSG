package com.kata.bankaccount.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.kata.bankaccount.entite.OperationEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDTO implements Serializable{

	private static final long serialVersionUID = 1L;

    private long idTransaction;
	
	private OperationEnum typeOperation;
    
    private LocalDateTime transactionDate;
    
    private long amount;
    
    private long balanceAfterOperation;
}
