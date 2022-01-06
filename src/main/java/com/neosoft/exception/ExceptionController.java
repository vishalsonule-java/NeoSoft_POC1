package com.neosoft.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.neosoft.config.PropConfig;
import com.neosoft.constant.AppConstant;

@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleUserNotFoundException(UserNotFoundException ex){
	
		ErrorInfo error= new ErrorInfo(LocalDateTime.now(),400, ex.getMessage());
		return new ResponseEntity<ErrorInfo>(error,HttpStatus.BAD_REQUEST);
		
	}
}
