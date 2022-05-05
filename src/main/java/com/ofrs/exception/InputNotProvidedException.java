package com.ofrs.exception;

public class InputNotProvidedException extends RuntimeException{

	/*
	 * @SpringToolSuit version 3.4.0
	 * @Date 15/04/2022
	 * InputNotProvidedException.java is a exception class.
	 */
	private static final long serialVersionUID = 1L;

	public InputNotProvidedException(String message) {
		super(message);
	}

}
