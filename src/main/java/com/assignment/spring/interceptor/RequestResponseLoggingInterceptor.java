package com.assignment.spring.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;


/**
 * This is the intercepter class for logging request and response
 * @author Nishchal
 *
 */
public class RequestResponseLoggingInterceptor implements ClientHttpRequestInterceptor {
	
	private static final Logger log = LoggerFactory.getLogger(RequestResponseLoggingInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		    logRequest(request, body);
	        ClientHttpResponse response = execution.execute(request, body);
	        logResponse(response);
	        return response;
	}

	
	/**
	 * This method is for logging the request
	 * @param request API request
	 * @param body body of the request
	 * @throws IOException Exception to be thrown
	 */
	private void logRequest(HttpRequest request, byte[] body) throws IOException {
	    
	   
	    
		log.info("===========================request begin================================================");
		log.info("URI         : {}"+" "+request.getURI());
		log.info("Method      : {}"+" "+ request.getMethod());
		log.info("Headers     : {}"+" "+request.getHeaders());
		log.info("Request body: {}"+" "+ new String(body, "UTF-8"));
		log.info("==========================request end================================================");
       
    }
	
	 /**
	  * This method is for logging the response
	 * @param response response from the API
	 * @throws IOException exception to be thrown
	 */
	private void logResponse(ClientHttpResponse response) throws IOException {
	  
       if(null != response ){
    	   log.info("============================response begin==========================================");
    	   log.info("Status code  : {}"+" "+ response.getStatusCode());
    	   log.info("Status text  : {}"+" "+response.getStatusText());
    	   log.info("Headers      : {}"+" "+response.getHeaders());
    	   log.info("Response body: {}"+" "+StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
    	   log.info("=======================response end=================================================");
       }else{
    	   log.info("============================response begin==========================================");
    	   log.info("Response value in null");
    	   log.info("=======================response end=================================================");
       }
	 }

}
