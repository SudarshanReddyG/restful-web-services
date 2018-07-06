package com.sudarshan.rest.restfulwebservices.user.exception;

import java.util.Date;

public class ExceptionResponse {
	
	private Date exceptionTime;
	
	private String message;
	
	private String description;

	public Date getExceptionTime() {
		return exceptionTime;
	}

	public void setExceptionTime(Date exceptionTime) {
		this.exceptionTime = exceptionTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ExceptionResponse(Date exceptionTime, String message, String description) {
		super();
		this.exceptionTime = exceptionTime;
		this.message = message;
		this.description = description;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [exceptionTime=" + exceptionTime + ", message=" + message + ", description="
				+ description + "]";
	}
}
