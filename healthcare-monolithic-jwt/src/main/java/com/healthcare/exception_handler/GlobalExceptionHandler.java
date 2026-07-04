package com.healthcare.exception_handler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.healthcare.custom_exceptions.ResourceNotFoundException;
import com.healthcare.dtos.ApiResponse;

/*
 * Declares a spring bean containing
 * - recurring common centralized exc handling
 * 
 */
@RestControllerAdvice // =@ControllerAdvice +@ResponseBody
public class GlobalExceptionHandler {
	// add exc handling method - to handle ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND) // SC 404
				.body(new ApiResponse(e.getMessage(), "Failed"));
	}

	// add exc handling method - to handle AuthenticationException : raised by Sprign security
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<?> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED) // SC 401
				.body(new ApiResponse(e.getMessage(), "Failed"));
	}

	// add exc handling method - to handle all remaining exceptions (catch-all)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		System.out.println("in catch -all");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // SC 500
				.body(new ApiResponse(e.getMessage(), "Failed"));
	}
	
	// add exc handling method - to handle P.L validation errors - MethodArgNotValid (@Valid)
		@ExceptionHandler(MethodArgumentNotValidException.class)
		@ResponseStatus(code = HttpStatus.BAD_REQUEST)
		public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
			System.out.println("in catch validation err ");
			//1. get list of rejected field errors
			List<FieldError> fieldErrList=e.getFieldErrors();
			//2. Convert List<FieldError> -> Map<String : FieldName,String : def mesg>
			Map<String, String> map = fieldErrList.stream() //Stream<FieldError>
			.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			//Conventional prog
//			Map<String,String> map=new HashMap<>();
//			fieldErrList.forEach(fieldErr -> map.put(fieldErr.getField(), fieldErr.getDefaultMessage()));
			return map;
		}

}
