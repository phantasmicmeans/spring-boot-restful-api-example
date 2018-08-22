package com.sw.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternerServerError extends RuntimeException{
	//request는 제대로 요청 됬으나, 데이터 없음. 데이터 없는경우는 404 하지 말자.
		private static final long serialVersionUID = 1L;

		public InternerServerError() {
			// TODO Auto-generated constructor stub
			super();
		}
		
		public InternerServerError(String message){
			
			super(message);
		}
		
		public InternerServerError(String message, Throwable cause){
			
			super(message, cause);
		}
		
		public InternerServerError(Throwable cause) {
			
			super(cause);
		}
		
		
	}
