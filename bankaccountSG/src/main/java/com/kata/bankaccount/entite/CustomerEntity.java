package com.kata.bankaccount.entite;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CUSTOMER_TABLE")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_CUSTOMER")
    private long id;

    @Column(name = "CUSTOMER_NAME")
    private String name;
    
    @OneToMany(targetEntity = AccountEntity.class, cascade = CascadeType.ALL, mappedBy = "customer")
    private List<AccountEntity> listAccount;
}
