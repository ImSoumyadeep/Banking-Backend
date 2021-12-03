package com.Edgeverve.demo.controller;

public class UserObject {
	
	private String id;
	private String encPassword;
	private double amount;
	private String recieverId; 
	private String status;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEncPassword() {
		return encPassword;
	}

	public void setEncPassword(String encPassword) {
		this.encPassword = encPassword;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(String recieverId) {
		this.recieverId = recieverId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserObject [id=" + id + ", encPassword=" + encPassword + ", amount=" + amount + ", recieverId="
				+ recieverId + ", status=" + status + "]";
	}
	
	
	
	
	
	
	
	

}
