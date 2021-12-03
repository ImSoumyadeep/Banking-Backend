package com.Edgeverve.demo.controller;

import java.nio.charset.Charset;
import java.sql.Date;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Edgeverve.demo.Services.EncyptionDecryption;
import com.Edgeverve.demo.dao.CustomerRepo;
import com.Edgeverve.demo.dao.TransactionRepo;
import com.Edgeverve.demo.model.Customer;
import com.Edgeverve.demo.model.Transactions;
import java.util.Random;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


@RestController
public class CustomerController {
	public String getAlphaNumericString(int n)
	{
		byte[] array = new byte[256];
		new Random().nextBytes(array);
		String randomString	= new String(array, Charset.forName("UTF-8"));
		StringBuffer r = new StringBuffer();
		for (int k = 0; k < randomString.length(); k++) 
		{ 
			char ch = randomString.charAt(k); 
			if (((ch >= 'a' && ch <= 'z')|| (ch >= 'A' && ch <= 'Z')||
					(ch >= '0' && ch <= '9'))&& (n > 0)) 
			{ 
				r.append(ch);
				n--;
			}
		}
		return r.toString();
	}	

	@Autowired 
	CustomerRepo repo;
	
	@Autowired 
	TransactionRepo trepo;
	
	@CrossOrigin
	@PostMapping("/customers")   // getting customers from the data base.
	@ResponseBody
	public List<Customer> getCustomers()
	{
		return repo.findAll();
		
		
	}
	
	@CrossOrigin
	@PostMapping(path="/customer",consumes= {MediaType.APPLICATION_JSON_VALUE})   // getting a particular customer from the Customer.
	//@ResponseBody
	
	public Optional<Customer> getCustomer(@RequestBody UserObject obj)
	{
		
		 return repo.findByAcid(obj.getId()); 
		
	}
	
	
	@CrossOrigin
	@PostMapping(path = "/login", consumes={MediaType.APPLICATION_JSON_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE})		
	public boolean validateUser(@RequestBody UserObject obj){
		String id =obj.getId();
		String encrypted=obj.getEncPassword();
		String decrypted = EncyptionDecryption.decryption(encrypted);
		Optional<Customer> dbCustomer = repo.findById(id);
		if(dbCustomer.isPresent())
		{
		    Customer c = dbCustomer.get();
		    
		    String pass=c.getDob().toString();
		    //System.out.println(d);
		    //System.out.println(dob.toString());
		    if(pass.equals(decrypted))
		    {
		    
		    	return true;
		    	
		    }
		}
	
		return false;
		
	}
		
	public String checkAccount(String acid)
	{
		Optional<Customer> dbCustomer = repo.findById(acid);  // checking wheather it is present in the database.
		if (dbCustomer.isEmpty())
			return "Invalid_Beneficiary_Account_ID";
		return "";
	}
	
	public String checkBalance(String acid,double amount)
	{
		Optional<Customer> dbCustomer = repo.findById(acid);     //check is present in database
		if(dbCustomer.isPresent())
		{
			Customer c = dbCustomer.get();    // getting current user account.
			if(c.getBal() < amount)
				return "Insufficient_Balance";
		}
		return "Invalid_User_Account_Id";
	}
		
	public String checkAmount(double amount)
	{
		if(amount< 0)
			return "Amount_can_not_be_negative";
		return "";
	}
	
	@CrossOrigin
	@PostMapping(path = "/transfer", consumes={MediaType.APPLICATION_JSON_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE})		
	public String transferMoney(@RequestBody UserObject obj) 
	{
		
		String acid = obj.getRecieverId();  // credit account
		String userid = obj.getId(); // debit account
		double amount = obj.getAmount();
		
		String isValid=checkAmount(obj.getAmount()) + " "+ checkAccount(acid)+" "+checkBalance(userid,amount);
		if(isValid.length()>5)
			return isValid;
		
		Optional<Customer> dbCustomer = repo.findById(acid); // receivers account is correct or not.
		//System.out.println(dbCustomer.toString());
		Customer receiver = dbCustomer.get();
		Optional<Customer> customer= repo.findById(userid);
		Customer sender = customer.get();    // getting current user account.
		sender.setBal(sender.getBal()-amount);
		receiver.setBal(receiver.getBal()+amount);
			
		repo.save(sender);
		repo.save(receiver);
		// transaction object;
		Transactions tt=new Transactions();
		String txn_id=getAlphaNumericString(15);
		tt.setTxnid(txn_id);
		tt.setDebit_acid(userid);  //setting debit account
		tt.setCredit_acid(acid);   // setting credit account
		tt.setAmount(amount);
		tt.setCurrency("INR");
		tt.setTxn_type(null);
		tt.setEffective_bal(0);
			
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		tt.setDate_of_transaction(dtf.format(now)+" IST");
		//System.out.println(dtf.format(now));
		trepo.save(tt);
		//System.out.println(tt);
		
		return "Successfull";  // Transaction is success
	}
	
	
	@CrossOrigin
	@PostMapping(path="/ministatement",consumes={MediaType.APPLICATION_JSON_VALUE})   // getting MiniStatements from the database.
	
	public List<Transactions> getTransactions(@RequestBody UserObject obj)
	{
		return trepo.findByAcid(obj.getId());
		
	}
}
