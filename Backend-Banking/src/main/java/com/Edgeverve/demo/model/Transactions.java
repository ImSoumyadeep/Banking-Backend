package com.Edgeverve.demo.model;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transactions {
		@Id
		@Column(name="txnid")
		private String txnid;
		
		@Column(name="debit_acid")
		private String debit_acid;
		
		@Column(name="credit_acid")
		private String credit_acid;
		
		@Column(name="date_of_transaction")
		private Date date_of_transaction;
		
		@Column(name="amount")
		private double amount;
		
		@Column(name="txn_type")
		private String txn_type;
		
		@Column (name="effective_bal")
		private double effective_bal;
		
		@Column (name="currency")
		private String currency;

		public String getTxnid() {
			return txnid;
		}

		public void setTxnid(String txnid) {
			this.txnid = txnid;
		}

		public String getDebit_acid() {
			return debit_acid;
		}

		public void setDebit_acid(String debit_acid) {
			this.debit_acid = debit_acid;
		}

		public String getCredit_acid() {
			return credit_acid;
		}

		public void setCredit_acid(String credit_acid) {
			this.credit_acid = credit_acid;
		}

		public Date getDate_of_transaction() {
			return date_of_transaction;
		}

		public void setDate_of_transaction(Date date_of_transaction) {
			this.date_of_transaction = date_of_transaction;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public String getTxn_type() {
			return txn_type;
		}

		public void setTxn_type(String txn_type) {
			this.txn_type = txn_type;
		}

		public double getEffective_bal() {
			return effective_bal;
		}

		public void setEffective_bal(double effective_bal) {
			this.effective_bal = effective_bal;
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		@Override
		public String toString() {
			return "Transactions [txnid=" + txnid + ", debit_acid=" + debit_acid + ", credit_acid=" + credit_acid
					+ ", date_of_transaction=" + date_of_transaction + ", amount=" + amount + ", txn_type=" + txn_type
					+ ", effective_bal=" + effective_bal + ", currency=" + currency + "]";
		}

		
}

