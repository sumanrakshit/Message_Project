package com.message.project.entity;

public class ErrorResponse {
	private String error;

//	public ErrorResponse(String error) {
//		super();
//		this.error = error;
//	}

//	public ErrorResponse() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ErrorResponse [error=" + error + "]";
	}
	
}
