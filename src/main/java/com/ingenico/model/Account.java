package com.ingenico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "account")
public class Account {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	 	
	 	@Column(name="balance")
	    private double balance;
	 	
	 	@Column(name="name")
	 	private String name;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public double getBalance() {
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	 	
	 	public static void main(String[] args) throws Exception{
	 		Account account = new Account();
	 		account.setBalance(1000);
	 		account.setName("test1");
	 		System.out.println(new ObjectMapper().writeValueAsString(account));
	 		System.out.println(new Object()==new Object());
	 	}
}
