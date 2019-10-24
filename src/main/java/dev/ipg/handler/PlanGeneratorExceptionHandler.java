package dev.ipg.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import dev.ipg.dto.ErrorResponse;
import dev.ipg.exception.ApplicationException;

@ControllerAdvice
public class PlanGeneratorExceptionHandler {
	
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException e) {
		ErrorResponse resp = new ErrorResponse();
		resp.setErrMsg(e.getMessage());
		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
	}
}
