package com.assignment.spring.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assignment.spring.constants.Constants;
import com.assignment.spring.dto.WeatherResponsedto;
import com.assignment.spring.entity.WeatherEntity;
import com.assignment.spring.model.WeatherResponse;
import com.assignment.spring.repository.WeatherRepository;
import com.assignment.spring.service.WeatherService;
import com.assignment.spring.utils.WeatherAPIValidators;

/**
 * This is the service layer for the Weather API
 * @author Nishchal
 *
 */
@Qualifier("weatherService")
@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WeatherRepository weatherRepository;

	@Autowired
	private WeatherResponsedto weatherApiResponse;


	/**
	 * This method makes call for the weather API and returns the response
	 * @param city Name of the city
	 */
	@Override
	public WeatherResponsedto getWeatherReport(String city) {
		
		WeatherAPIValidators.validateCity(city);
		String url = Constants.WEATHER_API_URL.replace("{city}", city).replace("{appid}", Constants.APP_ID);
		ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);
		WeatherAPIValidators.validateApiResponse(response);
		saveWeatherDetails(response.getBody());
		return setWeatherResponse(response.getBody());
   }

	/**
	 * This method saves the data to the database
	 * @param response response from the weather API
	 */
	private void saveWeatherDetails(WeatherResponse response) {
		WeatherEntity entity = new WeatherEntity();
		entity.setCity(response.getName());
		entity.setCountry(response.getSys().getCountry());
		entity.setTemperature(response.getMain().getTemp());
		weatherRepository.save(entity);
    }

	/**
	 * This method sets the response to response dto.
	 * @param response response from the weather API
	 * @return returns the dto object
	 */
	private WeatherResponsedto setWeatherResponse(WeatherResponse response) {
		weatherApiResponse.setCity(response.getName());
		weatherApiResponse.setCountry(response.getSys().getCountry());
		weatherApiResponse.setTemperature(response.getMain().getTemp());
		return weatherApiResponse;
	}

}
