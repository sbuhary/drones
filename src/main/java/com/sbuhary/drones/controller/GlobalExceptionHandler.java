package com.sbuhary.drones.controller;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sbuhary.drones.dto.ErrorResponseDTO;
import com.sbuhary.drones.exception.AlreadyExistsException;
import com.sbuhary.drones.exception.LimitExceededException;
import com.sbuhary.drones.exception.NotAllowedException;
import com.sbuhary.drones.exception.NotFoundException;

/**
 * 
 * @author SBUHARY
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Validation error. Check 'errors' field for details.");

		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errorResponse.addValidationErrorByField(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return ResponseEntity.unprocessableEntity().body(errorResponse);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest req) {

		ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Validation error. Check 'errors' field for details.");

		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errorResponse.addValidationErrorByField(violation.getPropertyPath().toString(), violation.getMessage());
		}

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
	}

	@ExceptionHandler({ AlreadyExistsException.class })
	protected ResponseEntity<Object> handleAlreadyExists(Exception ex, WebRequest req) {

		ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.CONFLICT.value(),
				"Already exists: " + ex.getMessage());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler({ NotFoundException.class })
	protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest req) {

		ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(),
				"Not found: " + ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@ExceptionHandler({ LimitExceededException.class })
	protected ResponseEntity<Object> handleLimitExceeded(Exception ex, WebRequest req) {

		ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.CONFLICT.value(),
				"Limit exceeds: " + ex.getMessage());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler({ NotAllowedException.class })
	protected ResponseEntity<Object> handleNotAllowed(Exception ex, WebRequest req) {

		ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.CONFLICT.value(),
				"Not allowed: " + ex.getMessage());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
}