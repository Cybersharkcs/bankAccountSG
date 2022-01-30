package com.kata.bankaccount.services;

import com.kata.bankaccount.dto.AccountDTO;
import com.kata.bankaccount.entite.OperationEnum;
import com.kata.bankaccount.exception.BankAccountException;

public interface IAccountService {
	public AccountDTO getAccount(long idAccount);
	
	public AccountDTO makeOperation(long idAccount, long amount, OperationEnum operationType) throws BankAccountException;
}
