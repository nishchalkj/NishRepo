package com.assignment.spring.service.implementation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.assignment.spring.dto.WeatherResponsedto;
import com.assignment.spring.exception.InvalidApiResponseException;
import com.assignment.spring.exception.InvalidCityNameException;
import com.assignment.spring.model.Main;
import com.assignment.spring.model.Sys;
import com.assignment.spring.model.WeatherResponse;
import com.assignment.spring.repository.WeatherRepository;

/**
 * This is the test class for service layer implementation
 * 
 * @author Nishchal
 *
 */
@RunWith(SpringRunner.class)
public class WeatherServiceImplTest {

	@InjectMocks
	private WeatherServiceImpl weatherServiceImpl;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private WeatherRepository weatherRepository;

	@Spy
	private WeatherResponsedto weatherApiResponse = new WeatherResponsedto();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void givenCorrectCityValueWhenApiIsCalledThenitReturnsSuccessResponse() {

		prepareDataForSuccessfulAPICall();

		WeatherResponsedto weatherResponsedto = weatherServiceImpl.getWeatherReport("Amsterdam");

		Assert.assertSame(weatherResponsedto.getCity(), "Amsterdam");

	}

	@Test(expected = InvalidCityNameException.class)
	public void givenEmptyCityValueWhenApiIsCalledThenInvalidCityNameExceptionIsThrown() {

		WeatherResponsedto weatherResponsedto = weatherServiceImpl.getWeatherReport("");

		Assert.assertSame(weatherResponsedto.getCity(), "Amsterdam");
	}

	@Test(expected = InvalidCityNameException.class)
	public void givenNumericCityValueWhenApiIsCalledThenInvalidCityNameExceptionIsThrown() {

		WeatherResponsedto weatherResponsedto = weatherServiceImpl.getWeatherReport("1234");

		Assert.assertSame(weatherResponsedto.getCity(), "Amsterdam");
	}

	@Test(expected = InvalidApiResponseException.class)
	public void givenApiResponseIsNullWhenApiIsCalledThenInvalidApiResponseExceptionIsThrown() {

		prepareDataForUnSuccessfulAPICall();

		WeatherResponsedto weatherResponsedto = weatherServiceImpl.getWeatherReport("Amsterdam");

		Assert.assertSame(weatherResponsedto.getCity(), "Amsterdam");

	}

	private void prepareDataForSuccessfulAPICall() {
		WeatherResponse weatherResponse = new WeatherResponse();
		Main main = new Main();
		Sys sys = new Sys();
		main.setTemp(282.73);
		sys.setCountry("NL");
		weatherResponse.setName("Amsterdam");
		weatherResponse.setMain(main);
		weatherResponse.setSys(sys);
		ResponseEntity<WeatherResponse> mockResponse = new ResponseEntity<>(weatherResponse, HttpStatus.OK);
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Matchers.eq(WeatherResponse.class)))
				.thenReturn(mockResponse);

	}

	private void prepareDataForUnSuccessfulAPICall() {
		WeatherResponse weatherResponse = new WeatherResponse();
		Main main = new Main();
		Sys sys = new Sys();
		main.setTemp(282.73);
		sys.setCountry("NL");
		weatherResponse.setName("Amsterdam");
		weatherResponse.setMain(main);
		weatherResponse.setSys(sys);
		ResponseEntity<WeatherResponse> mockResponse = new ResponseEntity<>(weatherResponse, HttpStatus.OK);
		Mockito.when(restTemplate.getForEntity("http://api.openweathermap.org/", WeatherResponse.class))
				.thenReturn(mockResponse);

	}

}
