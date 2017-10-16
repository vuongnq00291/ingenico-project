package com.ingenico.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TrasferInfo {
	private int fromAccountId;
	private int toAccountId;
	private double amount;
	
	public TrasferInfo(){};
	public TrasferInfo(int fromAccountId,int toAccountId,double amount){
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
		
	}
	
	public int getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public int getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public static void main(String[] args) throws Exception{
		TrasferInfo info = new TrasferInfo();
		info.setAmount(500);
		info.setFromAccountId(1);
		info.setToAccountId(2);
 		System.out.println(new ObjectMapper().writeValueAsString(info));
 		
 	}
	
}
