package com.ofrs.exception;

/*
 * @SpringToolSuit version 3.4.0
 * @Date 15/04/2022
 * RecordAlreadyPresentException.java is a exception class.
 * @RuntimeException is the superclass of those exceptions that can be thrown during the normal operation of the Java Virtual Machine.
 * 
 */
public class RecordAlreadyPresentException extends RuntimeException {
	public RecordAlreadyPresentException(String s) {
		super("Record already present...");
	}
}
