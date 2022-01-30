package com.kata.bankaccount.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kata.bankaccount.dto.AccountDTO;
import com.kata.bankaccount.dto.CustomerDTO;
import com.kata.bankaccount.dto.TransactionDTO;
import com.kata.bankaccount.entite.AccountEntity;
import com.kata.bankaccount.entite.OperationEnum;
import com.kata.bankaccount.entite.TransactionEntity;
import com.kata.bankaccount.exception.BankAccountException;
import com.kata.bankaccount.repository.IAccountRepository;

import org.springframework.transaction.annotation.Propagation;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AccountServiceImpl implements IAccountService{

    @Autowired
    private IAccountRepository accountRepository;

	@Override
	public AccountDTO getAccount(long idAccount) {
		return convertAccountEntityToDTO(accountRepository.getOne(idAccount));
	}

	@Override
	public AccountDTO makeOperation(long idAccount, long amount, OperationEnum operationType) throws BankAccountException {
		// Control amount
		if(amount <= 0) {
			throw new BankAccountException("The amount must be superior to zero");
		}
		// Get account
		AccountEntity accountEntity = accountRepository.getOne(idAccount);
		// Calculate new balance
		long balanceAfterDeposit = 0L;
		if(operationType.equals(OperationEnum.DEPOSIT)) {
			// Control amount
			if(amount > 10000) {
				throw new BankAccountException("The amount is too high, please go to agency");
			}
			balanceAfterDeposit = accountEntity.getBalance() + amount;	
		}
		if(operationType.equals(OperationEnum.WITHDRAWAL)) {
			// Control amount
			if(amount > accountEntity.getBalance()) {
				throw new BankAccountException("Insufficient founds");
			}
			balanceAfterDeposit = accountEntity.getBalance() - amount;
		}
		accountEntity.setBalance(balanceAfterDeposit);
		// Add transaction
		TransactionEntity transaction = new TransactionEntity();
		transaction.setAccount(accountEntity);
		transaction.setAmount(amount);
		transaction.setBalanceAfterOperation(balanceAfterDeposit);
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTypeOperation(operationType);
		accountEntity.getListTransaction().add(transaction);
		// Save account
		return convertAccountEntityToDTO(accountRepository.save(accountEntity));
	}
	
	public AccountDTO convertAccountEntityToDTO(AccountEntity accountEntity){
		return AccountDTO.builder().balance(accountEntity.getBalance())
				.customer(CustomerDTO.builder().name(accountEntity.getCustomer().getName()).build())
					.listTransaction(accountEntity.getListTransaction().stream()
							.map(transaction -> TransactionDTO.builder().amount(transaction.getAmount())
									.balanceAfterOperation(transaction.getBalanceAfterOperation())
										.transactionDate(transaction.getTransactionDate()).idTransaction(transaction.getId())
											.typeOperation(transaction.getTypeOperation()).build()).distinct()
											.collect(Collectors.toList())).build();
	}
	
}
