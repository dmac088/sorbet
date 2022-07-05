package io.nzbee.exceptions;


public final class AlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AlreadyExistsException() {
        super();
    }
    
    public AlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistsException(final String message) {
        super(message);
    }

    public AlreadyExistsException(final Throwable cause) {
        super(cause);
    }

}
