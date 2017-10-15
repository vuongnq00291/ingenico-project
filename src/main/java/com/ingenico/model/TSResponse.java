package com.ingenico.model;

public class TSResponse {
	private int status=1; //1 is successful, 0 is fail.
	private String error = "Request successfull, no exception";
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		status = 0;
		this.error = error;
	}
	
	
}
