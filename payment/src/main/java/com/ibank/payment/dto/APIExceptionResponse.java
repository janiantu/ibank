package com.ibank.payment.dto;

import java.util.List;

public class APIExceptionResponse {
	
	private String status;

	private String message;

	private List<String> errors;
	 
	public APIExceptionResponse(String status, String message, List<String> errors) {
		this.status = status;
		this.message = message;
		this.errors = errors;
	}
	
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	 
	 

}
