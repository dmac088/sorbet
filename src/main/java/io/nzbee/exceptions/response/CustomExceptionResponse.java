package io.nzbee.exceptions.response;

public class CustomExceptionResponse {

	private String code;
	private String description;
	
	public CustomExceptionResponse(String code, String response) {
		this.setDescription(response);
		this.setCode(code);
	}
	
	public CustomExceptionResponse(String code, String response, String cause) {
		this.setDescription(response);
		this.setCode(code);
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String message) {
		this.description = message;
	}
	
}
