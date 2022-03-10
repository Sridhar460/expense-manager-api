package com.example.expense.customexception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6624731722302226394L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
