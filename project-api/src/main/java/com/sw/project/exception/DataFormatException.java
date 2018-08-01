package com.sw.project.exception;

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
