package com.potlocker.exception;

public class InvalidEmail extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String errorType;
	String errorDescription;
	
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
}
