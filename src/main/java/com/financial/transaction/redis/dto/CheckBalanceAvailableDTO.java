package com.financial.transaction.redis.dto;

import java.io.Serializable;

public class CheckBalanceAvailableDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cpf;
	private String numberCard;
	private boolean availability;

	public CheckBalanceAvailableDTO() {
	}

	public CheckBalanceAvailableDTO(String cpf, String numberCard, boolean availability) {
		this.cpf = cpf;
		this.numberCard = numberCard;
		this.availability = availability;
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

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

}
