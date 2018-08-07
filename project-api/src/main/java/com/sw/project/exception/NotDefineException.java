package com.sw.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotDefineException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NotDefineException() {
		super();
	}
	
	public NotDefineException(String message) {
		
		super(message);
	}
	
}

