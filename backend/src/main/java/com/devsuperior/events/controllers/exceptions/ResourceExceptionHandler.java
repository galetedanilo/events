package com.devsuperior.events.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.events.services.exceptions.DatabaseException;
import com.devsuperior.events.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
		
		StandardError error = new StandardError();
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Resource Not Found");
		error.setMessage(exception.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> databaseException(DatabaseException exception, HttpServletRequest request) {
		
		StandardError error = new StandardError();
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Database Exception");
		error.setMessage(exception.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException exception, HttpServletRequest request) {
		
		ValidationErrors error = new ValidationErrors();
		
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Validation Exception");
		error.setMessage(exception.getMessage());
		error.setPath(request.getRequestURI());
		
		for(FieldError field : exception.getBindingResult().getFieldErrors()) {
			error.addError(field.getField(), field.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(error);
	}

}
