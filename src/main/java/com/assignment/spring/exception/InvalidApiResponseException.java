package com.assignment.spring.exception;

/**
 * This exception is thrown when API response is invalid
 * @author Nishchal
 *
 */
public class InvalidApiResponseException extends WeatherReportException{


	private static final long serialVersionUID = 1964822138452787937L;

	public InvalidApiResponseException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
	
	public InvalidApiResponseException(final String msg) {
		super(msg);
	}

}
