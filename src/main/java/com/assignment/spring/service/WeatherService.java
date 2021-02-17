package com.assignment.spring.service;

import com.assignment.spring.dto.WeatherResponsedto;
import com.assignment.spring.exception.InvalidCityNameException;


/**
 * This is the interface for service layer
 * @author Nishchal
 *
 */
public interface WeatherService {

	
	/**
	 * This method makes call for the weather API and returns the response
	 * @param city Name of the city
	 */
	public WeatherResponsedto getWeatherReport(String city);
}
	