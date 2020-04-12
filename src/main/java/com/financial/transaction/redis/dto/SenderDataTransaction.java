package com.financial.transaction.redis.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SenderDataTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cpf;
	private String numberCard;
	private String password;
	private BigDecimal value;

	public SenderDataTransaction() {
		// TODO Auto-generated constructor stub
	}

	public SenderDataTransaction(String numberCard, String password, BigDecimal value) {
		this.numberCard = numberCard;
		this.password = password;
		this.value = value;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
