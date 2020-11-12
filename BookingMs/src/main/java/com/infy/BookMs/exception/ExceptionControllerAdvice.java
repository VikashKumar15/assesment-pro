package com.infy.BookMs.exception;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infy.BookMs.dto.ErrorMessage;

@Component
@PropertySource("classpath:messages.properties")
@RestControllerAdvice
public class ExceptionControllerAdvice {
	@Autowired
	private Environment environment;
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(WeCareException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler(WeCareException e) {
		ErrorMessage error = new ErrorMessage();
		error.setMessage(environment.getProperty(e.getMessage()));
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
}
