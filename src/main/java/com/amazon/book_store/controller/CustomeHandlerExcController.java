package com.amazon.book_store.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazon.book_store.Exc.NotFoundExc;
import com.amazon.book_store.errorModel.NotFoundErrorResponse;

@ControllerAdvice
public class CustomeHandlerExcController {


	@ExceptionHandler
	public ResponseEntity<NotFoundErrorResponse>  notFoundExc(NotFoundExc exc) {
		
		NotFoundErrorResponse error = new NotFoundErrorResponse(
					HttpStatus.NOT_FOUND.value() ,
					exc.getMessage()
				);
	
		return new ResponseEntity<NotFoundErrorResponse>(error,HttpStatus.NOT_FOUND) ;
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> notValid(MethodArgumentNotValidException exc) {
		
		
		List<String> errors = new ArrayList<>() ;
		
		errors = exc.getBindingResult().getAllErrors().stream().map(
					error -> {
						return error.getDefaultMessage() ;
					}
				).collect(Collectors.toList());
		
		
		LinkedHashMap<String , List<String>> resp = new LinkedHashMap<>();
		resp.put("errors", errors);	
		
		return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> sqlExc(SQLIntegrityConstraintViolationException exc) {
		
		
		LinkedHashMap<String, Object> resp = new LinkedHashMap<>();

		resp.put("status", HttpStatus.BAD_REQUEST.value());
		resp.put("errors", exc.getMessage());	

		
		return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<?> handleException(Exception exc) {
		
		LinkedHashMap<String, Object> resp = new LinkedHashMap<>();

		resp.put("status", HttpStatus.BAD_REQUEST.value());
		resp.put("errors", exc.getMessage());	
		
		return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
	}
	
}
