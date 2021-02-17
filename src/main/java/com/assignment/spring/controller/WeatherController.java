package com.assignment.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.spring.dto.WeatherResponsedto;
import com.assignment.spring.service.WeatherService;


/**
 * Controller class for the weather controller API
 * @author Nishchal
 *
 */
@RestController
public class WeatherController {

    @Autowired 
	private WeatherService weatherService;
	
   
    /**
     * This methods calls the service layer to call weather API
     * @param city city name for weather API
     * @return weather details in json format
     */
    @GetMapping(value="/weather")
    public WeatherResponsedto weather(@RequestParam("city") String city) {
        
        return weatherService.getWeatherReport(city);
      
    }

   
}
