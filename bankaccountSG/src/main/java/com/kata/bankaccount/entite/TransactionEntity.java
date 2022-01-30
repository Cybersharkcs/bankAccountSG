package com.kata.bankaccount.entite;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "TRANSACTION_TABLE")
public class TransactionEntity {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_TRANSACTION")
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "ID_ACCOUNT")
    private AccountEntity account;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "OPERATION_TYPE")
    private OperationEnum typeOperation;

    @Column(name = "TRANSACTION_DATE")
    private LocalDateTime transactionDate;
    
    @Column(name = "TRANSACTION_AMOUNT")
    private long amount;
    
    @Column(name = "BALANCE_AFTER_OPERATION")
    private long balanceAfterOperation;
}
