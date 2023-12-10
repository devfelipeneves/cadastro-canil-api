package com.unigoias.canil.domain.exception.globalhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.unigoias.canil.domain.exception.CaninoExistsException;
import com.unigoias.canil.domain.exception.CaninoNotFoundException;
import com.unigoias.canil.domain.exception.RacaExistsException;
import com.unigoias.canil.domain.exception.RacaIntegrityException;
import com.unigoias.canil.domain.exception.RacaNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RacaExistsException.class)
	public ResponseEntity<String> handleRacaExistsException(RacaExistsException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}
	
	@ExceptionHandler(RacaNotFoundException.class)
	public ResponseEntity<String> handleRacaNotFoundException(RacaNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(RacaIntegrityException.class)
	public ResponseEntity<String> handleRacaIntegrityException(RacaIntegrityException ex) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
	}
	
	@ExceptionHandler(CaninoExistsException.class)
	public ResponseEntity<String> handleCaninoExistsException(CaninoExistsException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}
	
	@ExceptionHandler(CaninoNotFoundException.class)
	public ResponseEntity<String> handleCaninoNotFoundException(CaninoNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
