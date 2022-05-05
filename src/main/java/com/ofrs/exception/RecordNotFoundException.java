package com.ofrs.exception;



/*
 * @SpringToolSuit version 3.4.0
 * @Date 15/04/2022
 * RecordNotFoundException.java is a exception class.
 * @RuntimeException is the superclass of those exceptions that can be thrown during the normal operation of the Java Virtual Machine.
 * 
 */
public class RecordNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String msg) {
		super(msg);
	}

	
}
