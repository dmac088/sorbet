package io.nzbee.exceptions;


public final class PasswordsNotMatchException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PasswordsNotMatchException() {
        super();
    }

    public PasswordsNotMatchException(final String message) {
        super(message);
    }

    public PasswordsNotMatchException(final Throwable cause) {
        super(cause);
    }

}
