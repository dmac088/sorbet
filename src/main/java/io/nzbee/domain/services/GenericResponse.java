package io.nzbee.domain.services;


public class GenericResponse {
    private String message;

    public GenericResponse(final String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

}
