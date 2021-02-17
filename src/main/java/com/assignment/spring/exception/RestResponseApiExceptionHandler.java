package com.assignment.spring.exception;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assignment.spring.constants.Constants;
import com.assignment.spring.dto.WeatherExceptionResponse;

/**
 * This is the global exception handler for weather API
 * @author Nishchal
 *
 */
@ControllerAdvice
public class RestResponseApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * This method handles the custom exception
	 * @param ex WeatherReportException - exception thrown	
	 * @return ResponseEntity<WeatherExceptionResponse>
	 */
	@ExceptionHandler(WeatherReportException.class)
	 public ResponseEntity<WeatherExceptionResponse> customExceptionHandler(WeatherReportException ex){
		
		WeatherExceptionResponse errorResponse = new WeatherExceptionResponse();
		errorResponse.setDescription(ex.getMessage());
		errorResponse.setStatusMsg(Constants.ERROR_MSG);
		errorResponse.setTimeStamp(LocalDateTime.now(ZoneId.of("Europe/Amsterdam")));
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
		 
		 
	 }

}
