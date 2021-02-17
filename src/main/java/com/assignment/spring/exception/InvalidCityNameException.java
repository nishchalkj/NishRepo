package com.assignment.spring.exception;

/**
 * This exception is thrown when the given city name is invalid
 * @author Nishchal
 *
 */
public class InvalidCityNameException extends WeatherReportException{

    private static final long serialVersionUID = 1964822138452787937L;

	public InvalidCityNameException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
	
	public InvalidCityNameException(final String msg) {
		super(msg);
	}

}
