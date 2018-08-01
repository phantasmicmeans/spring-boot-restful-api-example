package com.sw.project.exception;

public class ElementNullException  extends RuntimeException{

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

