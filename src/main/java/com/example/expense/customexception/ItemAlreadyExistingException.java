package com.example.expense.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ItemAlreadyExistingException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7299606381952337139L;

	public ItemAlreadyExistingException(String message) {
		super(message);
	}
}
