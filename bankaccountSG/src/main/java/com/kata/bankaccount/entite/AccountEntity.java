package com.kata.bankaccount.entite;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ACCOUNT_TABLE")
public class AccountEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_ACCOUNT")
    private long id;
    
    @Column(name = "BALANCE")
    private long balance;
    
    @OneToMany(targetEntity = TransactionEntity.class, cascade = CascadeType.ALL, mappedBy = "account")
    private List<TransactionEntity> listTransaction;
    
    @ManyToOne
    @JoinColumn(name = "ID_CUSTOMER")
    private CustomerEntity customer;

  
}
