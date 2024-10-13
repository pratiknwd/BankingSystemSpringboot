package com.virtusa.banking.exception;

public class CustomDatabaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public CustomDatabaseException() {

	}

	public CustomDatabaseException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
