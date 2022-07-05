package io.nzbee.exceptions;

import java.util.List;

import org.springframework.validation.ObjectError;

public final class GeneralDomainException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final List<ObjectError> errors;
    
    public GeneralDomainException(List<ObjectError> errors, final String message) {
        super(message);
        this.errors = errors;
    }
    
	public List<ObjectError> getErrors() {
		return errors;
	}
    

}
