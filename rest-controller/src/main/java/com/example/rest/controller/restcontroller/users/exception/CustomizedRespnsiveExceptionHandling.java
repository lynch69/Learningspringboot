package com.example.rest.controller.restcontroller.users.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.rest.controller.restcontroller.users.UserNotFoundException;

@ControllerAdvice
public class CustomizedRespnsiveExceptionHandling extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails>	handleAllExceptions(Exception ex, WebRequest request){
		
		ErrorDetails errordetails = new ErrorDetails(LocalDate.now(),ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
		
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails>	handleUsernotFoundExceptions(Exception ex, WebRequest request){
		
		ErrorDetails errordetails = new ErrorDetails(LocalDate.now(),ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.NOT_FOUND);
		
		
		
	}
	

}
