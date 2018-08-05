package com.sw.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public final class ResourceNotFoundException extends RuntimeException{
//data invalid
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException() {
		
		super(); //Runtime Exception super class 
	} 
	
	public ResourceNotFoundException(String message){
		super(message);
	}
	
	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
