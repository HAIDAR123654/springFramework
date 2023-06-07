package com.codedecode.demo.custom.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleFindAllEmployeeException(BusinessException be){
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("error message", be.getErrorMessage());
		errorMap.put("error code", be.getErrorCode());
		return ResponseEntity.ok(errorMap);
		
	}
	
}
