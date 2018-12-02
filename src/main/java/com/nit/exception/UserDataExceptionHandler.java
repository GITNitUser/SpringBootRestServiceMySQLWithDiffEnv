package com.nit.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nit.model.UserDataError;

@ControllerAdvice
public class UserDataExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserDataException.class)
	public ResponseEntity handleUserDataException(UserDataException ude){
		UserDataError userDataError=null; 
		
		userDataError=new UserDataError(420,ude.getMessage(), ude.getLocalizedMessage());
		
		 return  ResponseEntity.status(420).body(userDataError);
	}

}
