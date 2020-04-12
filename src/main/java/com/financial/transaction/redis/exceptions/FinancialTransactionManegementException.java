package com.financial.transaction.redis.exceptions;

public class FinancialTransactionManegementException extends Exception {
	private static final long serialVersionUID = 1L;

	public FinancialTransactionManegementException(String message) {
        super(message);
    }
}