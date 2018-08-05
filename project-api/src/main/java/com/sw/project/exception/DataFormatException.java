package com.sw.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public final class DataFormatException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DataFormatException() {
		super();
	}
	
	public DataFormatException(String message){
		
		super(message);
	}
	
	public DataFormatException(String message, Throwable cause){
		
		super(message, cause);
	}
	
	public DataFormatException(Throwable cause) {
		
		super(cause);
	}
	
	
}
