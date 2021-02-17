package com.assignment.spring.exception;


/**
 * This is the base exception class for all the exceptions
 * @author Nishchal
 *
 */
public class WeatherReportException extends RuntimeException {


	private static final long serialVersionUID = -5349303699129514616L;

	public WeatherReportException(final String msg) {
		super(msg);
	}

	public WeatherReportException(final String msg, final Throwable throwable) {
		super(msg, throwable);
	}

}
	