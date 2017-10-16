package com.ingenico.model;

public final class TSResponse {
	private int status=1; //1 is successful, 0 is fail.
	private String error = "Request successfull, no exception";
	
	public TSResponse(){};
	public TSResponse(int status,String error){
		this.status = status;
		this.error = error;
	}
	public int getStatus() {
		return status;
	}
	public String getError() {
		return error;
	}
	
}
