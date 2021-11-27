package com.Edgeverve.demo.controller;

import java.nio.charset.Charset;
import java.sql.Date;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

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


	
	
	private String userId;
	

	@Autowired 
	CustomerRepo repo;
	
	@Autowired 
	TransactionRepo trepo;
	
	@CrossOrigin
	@GetMapping("/customers")   // getting customers from the data base.
	@ResponseBody
	public List<Customer> getCustomers()
	{
		return repo.findAll();
		
		
	}
	
	@CrossOrigin
	@GetMapping("/customer/{acid}")   // getting a particular customer from the Customer.
	@ResponseBody
	public Optional<Customer> getCustomer(@PathVariable String acid)
	{
		 
		return repo.findByAcid(acid); 
		
		
	}
	
	
	@CrossOrigin
	@PostMapping("/login/{acid}/{dob}")
	@ResponseBody
	public boolean login(@PathVariable String acid, @PathVariable Date dob)
	{
		Optional<Customer> dbCustomer = repo.findById(acid);
		//System.out.println(dbCustomer.toString());
		if(dbCustomer.isPresent())
		{
		    Customer c = dbCustomer.get();
		    
		    String d=c.getDob().toString();
		    //System.out.println(d);
		    //System.out.println(dob.toString());
		    if(d.equals(dob.toString()))
		    {
		    	
		    	userId=c.getAcid();
		    	
		    	return true;
		    	
		    }
		}
		 return false;
	}
	
	public boolean checkRecevierAndBalance(String acid,double amount)
	{
		Optional<Customer> dbCustomer = repo.findById(acid); // receivers account is correct or not.
		//System.out.println(dbCustomer.toString());
		if(dbCustomer.isPresent())
		{
			Optional<Customer> customer= repo.findById(userId);
			Customer c = customer.get();    // getting current user account.
			if(c.getBal() >= amount)
			{
				return true;
			}
			
		}
		return false;
	}
		
		
	
	@CrossOrigin
	@PostMapping("/add/{acid}/{amount}")
	@ResponseBody
	public String transferMoney(@PathVariable String acid, @PathVariable double amount)
	{
		if(checkRecevierAndBalance(acid,amount))
		{
			Optional<Customer> dbCustomer = repo.findById(acid); // receivers account is correct or not.
			//System.out.println(dbCustomer.toString());
			Customer receiver = dbCustomer.get();
			Optional<Customer> customer= repo.findById(userId);
			Customer sender = customer.get();    // getting current user account.
			sender.setBal(sender.getBal()-amount);
			receiver.setBal(receiver.getBal()+amount);
			repo.save(sender);
			repo.save(receiver);
			// transaction object;
			Transactions tt=new Transactions();

			String txn_id=getAlphaNumericString(15);
			tt.setTxnid(txn_id);
			tt.setDebit_acid(userId);
			tt.setCredit_acid(acid);
			tt.setAmount(amount);
			tt.setCurrency("INR");
			tt.setTxn_type(null);
			tt.setEffective_bal(0);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			tt.setDate_of_transaction(dtf.format(now));
			//System.out.println(dtf.format(now));
			trepo.save(tt);
			System.out.println(tt);
			
			return "Success";
			
			
		}
		return "Fail";
	}
	
	/*@PostMapping("/add")
	public Customer addCustomer(@RequestBody Customer cust)
	{
		repo.save(cust);
		return cust;
	}
	*/
	
	
	/*@GetMapping("/transactions")   // getting transactions from the database.
	@ResponseBody
	public List<Transactions> getTransactions(@PathVariable String acid)
	{
		return trepo.findByAcid(acid);
		
	}
	*/
	
	
	
	@CrossOrigin
	@GetMapping("/transactions/{acid}")   // getting transactions from the database.
	@ResponseBody
	public Transactions getTransactions(@PathVariable String acid)
	{
		Transactions tc= trepo.findByAcid_parti(acid);
		double k=tc.getEffective_bal()+2000;
		tc.setEffective_bal(k);
		System.out.println(tc.toString());
		trepo.save(tc);
		return tc;
		
	}
	

	

}
