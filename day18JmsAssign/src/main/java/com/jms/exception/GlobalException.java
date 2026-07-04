package com.jms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jms.entities.ApiResponseDTO;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler
	public ResponseEntity<?> handleResourceNotFound(ResourceNotFound e){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDTO(e.getMessage(),"Failed"));
	}
	
	
	@ExceptionHandler
	public ResponseEntity<?> ec(Exception e){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDTO(e.getMessage(),"Failed"));
	}
}
