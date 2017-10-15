package com.ingenico.model;

public class TSException extends Exception{

	private String error;
	private static final long serialVersionUID = 1481639874100725832L;
	
	public TSException(String error){
		this.setError(error);
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

}
