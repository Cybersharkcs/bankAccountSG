package com.kata.bankaccount.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO implements Serializable {
	private String name;
}
