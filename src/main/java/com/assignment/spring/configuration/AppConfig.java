package com.assignment.spring.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.assignment.spring.interceptor.RequestResponseLoggingInterceptor;

/**
 * Configuration class which registers the rest template bean with spring container.
 * Intercepter is set for logging the request and response
 * @author Nishchal
 *
 */
@Configuration
public class AppConfig {
	
	ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
    /**
     * Returns the rest template object
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
    	 RestTemplate restTemplate =  new RestTemplate(factory);
         restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
         return restTemplate;
    }
}
