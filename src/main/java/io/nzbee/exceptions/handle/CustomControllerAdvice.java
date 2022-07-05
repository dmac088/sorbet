package io.nzbee.exceptions.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import io.nzbee.ErrorKeys;
import io.nzbee.exceptions.AlreadyExistsException;
import io.nzbee.exceptions.EntityNotFoundException;
import io.nzbee.exceptions.PasswordsNotMatchException;
import io.nzbee.exceptions.response.CustomExceptionResponse;

@ControllerAdvice
public class CustomControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler(AlreadyExistsException.class)
	public final ResponseEntity<CustomExceptionResponse> alreadyExistsResponse(AlreadyExistsException ex) {
		CustomExceptionResponse response = new CustomExceptionResponse(ErrorKeys.customerAlreadyExists, ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	@ResponseBody
	@ExceptionHandler(PasswordsNotMatchException.class)
	public final ResponseEntity<CustomExceptionResponse> passwordsNotMatchResponse(PasswordsNotMatchException ex) {
		CustomExceptionResponse response = new CustomExceptionResponse(ErrorKeys.passwordsDontMatch, ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	@ResponseBody
	@ExceptionHandler(EntityNotFoundException.class)
	public final ResponseEntity<CustomExceptionResponse> notFoundResponse(EntityNotFoundException ex) {
		CustomExceptionResponse response = new CustomExceptionResponse(ex.getErrorCode(), ex.getLocalizedMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
}
