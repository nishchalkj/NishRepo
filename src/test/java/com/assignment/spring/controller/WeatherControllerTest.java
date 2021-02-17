package com.assignment.spring.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.assignment.spring.dto.WeatherResponsedto;
import com.assignment.spring.service.WeatherService;

/**
 * This is the test class for the weather controller
 * 
 * @author Nishchal
 *
 */
@RunWith(SpringRunner.class)
public class WeatherControllerTest {

	@InjectMocks
	private WeatherController controller;

	@Mock
	private WeatherService weatherService;

	private MockMvc mockMvc;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void givenProperRequestWhenControllerIsCalledthenRespondsStatusOk() throws Exception {
		Mockito.when(weatherService.getWeatherReport(Mockito.anyString())).thenReturn(new WeatherResponsedto());
		mockMvc.perform(get("/weather?city=Amsterdam")).andExpect(status().isOk());
		verify(weatherService, times(1)).getWeatherReport(Mockito.anyString());
	}

	@Test
	public void givenIncorrectRequestWhenControllerIsCalledThenRespondStatusAsNotFound() throws Exception {
		Mockito.when(weatherService.getWeatherReport(Mockito.anyString())).thenReturn(new WeatherResponsedto());
		mockMvc.perform(get("/weathercity=Amsterdam")).andExpect(status().isNotFound());
		verify(weatherService, times(0)).getWeatherReport(Mockito.anyString());

	}

	@Test
	public void givenIncorrectRequestMethodWhenControllerIsCalledThenRespondStatusMethodNotAllowed() throws Exception {
		Mockito.when(weatherService.getWeatherReport(Mockito.anyString())).thenReturn(new WeatherResponsedto());
		mockMvc.perform(post("/weather?city=Amsterdam")).andExpect(status().isMethodNotAllowed());
		verify(weatherService, times(0)).getWeatherReport(Mockito.anyString());

	}

}
