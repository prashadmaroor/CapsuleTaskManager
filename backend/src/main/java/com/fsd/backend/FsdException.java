/*
 * 
 */
package com.fsd.backend;

/**
 * The Class FsdException.
 *
 * @author Prasad Pai M
 */

public class FsdException extends Exception {

	/**
	 * Instantiates a new fsd exception.
	 */
	public FsdException() {
		super();
	}


	/**
	 * Instantiates a new fsd exception.
	 *
	 * @param errorMessage
	 *            the error message
	 * @param errorCaught
	 *            the error caught
	 */
	public FsdException(String errorMessage, Throwable errorCaught) {
		super(errorMessage, errorCaught);
		
	}
	

}
