package com.assignment.spring.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;

import com.assignment.spring.exception.InvalidApiResponseException;
import com.assignment.spring.exception.InvalidCityNameException;
import com.assignment.spring.model.WeatherResponse;


/**
 * The utility class.
 * @author Nishchal
 *
 */
public final class WeatherAPIValidators {
	
	
	/**
	 * This method validates the city parameter and throws exception accordingly.
	 * @param city Name of the city
	 */
	public static void validateCity(String city) {
		if (StringUtils.isBlank(city) || !StringUtils.isAlpha(city)) {
			throw new InvalidCityNameException("Invalid city name.Please provide proper city name");
		}
	}
	
	
	/**
	 * This method validates the response of the Weather API and throws exception accordingly.
	 * @param response repose from the weather API
	 */
	public static void validateApiResponse(ResponseEntity<WeatherResponse> response){
		if(null == response){
			throw new InvalidApiResponseException("Processing fail. Got a null response from the weather API");
			}
	}

}
