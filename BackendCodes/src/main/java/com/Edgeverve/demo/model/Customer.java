package com.Edgeverve.demo.model;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	@Column(name="acid")
	private String acid;
	@Column(name="name")
	private String name;
	@Column(name="dob")
	private Date dob;
	
	@Column(name="bal")
	private double bal;
	
	@Column(name="lim")
	private double lim;
	
	@Column(name="lein")
	private double lein;
	
	public String getAcid() {
		return acid;
	}
	public void setAcid(String acid) {
		this.acid = acid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public double getBal() {
		return bal;
	}
	public void setBal(double bal) {
		this.bal = bal;
	}
	public double getLim() {
		return lim;
	}
	public void setLim(double lim) {
		this.lim = lim;
	}
	public double getLein() {
		return lein;
	}
	public void setLein(double lein) {
		this.lein = lein;
	}
	
	@Override
	public String toString() {
		return "Customer [acid=" + acid + ", name=" + name + ", dob=" + dob + ", bal=" + bal + ", lim=" + lim
				+ ", lein=" + lein + "]";
	}

}
