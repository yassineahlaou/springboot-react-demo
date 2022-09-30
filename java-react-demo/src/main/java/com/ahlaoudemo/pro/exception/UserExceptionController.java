package com.ahlaoudemo.pro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  //annotation to handle exceptions globally
public class UserExceptionController {
	@ExceptionHandler(value = EmailAlreadyExistException.class)
	   public ResponseEntity<Object> exception(EmailAlreadyExistException exception) {
	      return new ResponseEntity<>("This Email Already Exist", HttpStatus.NOT_FOUND);
	   }

}
