package com.sw.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class ElementNullException  extends RuntimeException{
//request는 제대로 요청 됬으나, 데이터 없음. 데이터 없는경우는 404 하지 말자.
	private static final long serialVersionUID = 1L;

	public ElementNullException() {
		super();
	}
	
	public ElementNullException(String message){
		
		super(message);
	}
	
	public ElementNullException(String message, Throwable cause){
		
		super(message, cause);
	}
	
	public ElementNullException(Throwable cause) {
		
		super(cause);
	}
	
	
}

