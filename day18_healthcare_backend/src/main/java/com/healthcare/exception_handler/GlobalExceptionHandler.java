package com.healthcare.exception_handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.healthcare.custom_exceptions.AuthenticationException;
import com.healthcare.custom_exceptions.InvalidInputException;
import com.healthcare.custom_exceptions.ResourceNotFoundException;
import com.healthcare.dtos.ApiResponse;

/*
 * To declare a spring bean
 * - containing common exc handling logic
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	// to declare exc handling method - to handle ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
		System.out.println("in res not found exc");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "Failed"));
	}

	// to declare exc handling method - to handle InvalidInputException
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<?> handleInvalidInputException(InvalidInputException e) {
		System.out.println("in invalid input exc");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), "Failed"));
	}

	// to declare exc handling method - to handle AuthenticationException
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<?> handleAuthenticationException(AuthenticationException e) {
		System.out.println("in auth exc");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(e.getMessage(), "Failed"));
	}

	// catch -all
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		System.out.println("in catch-all exc");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), "Failed"));
	}

	// validation failure
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		System.out.println("in @Valid  exc");
		// get list of rejected fields
		List<FieldError> fieldErrList = e.getFieldErrors();

		// for better clarity , convert List -> Map<String-name, String - message>
		Map<String, String> fieldErrMap = fieldErrList.stream() // Stream<FieldError>
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
//		Map<String, String> fieldErrMap2=new HashMap<>();
//		fieldErrList.forEach(f -> fieldErrMap2.put(f.getField(), f.getDefaultMessage()));
//		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldErrMap);

	}

}
