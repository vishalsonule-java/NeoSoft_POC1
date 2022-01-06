package com.neosoft.exception;

public class UserNotFoundException extends RuntimeException {
	
	/**
	 * default id
	 */
	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException() {
		super();
		
	}
	
	public UserNotFoundException(String errorMsg) {
		super(errorMsg);
	}

}
