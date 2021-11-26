package com.Edgeverve.demo.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
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



@RestController
public class CustomerController {
	@Autowired 
	CustomerRepo repo;
	
	@Autowired 
	TransactionRepo trepo;
	
	@GetMapping("/customers")   // getting customers from the data base.
	@ResponseBody
	public List<Customer> getCustomers()
	{
		return repo.findAll();
		
		
	}
	@GetMapping("/get/{acid}")   // getting a single customer from the Customer.
	@ResponseBody
	public Optional<Customer> getCustomer(@PathVariable String acid)
	{
		return repo. findByAcid(acid);
		
		
	}
	
	@PostMapping("/add")
	public Customer addCustomer(@RequestBody Customer cust)
	{
		repo.save(cust);
		return cust;
	}
	
	
	
	@GetMapping("/transactions/{acid}")   // getting transactions from the database.
	@ResponseBody
	public List<Transactions> getTransactions(@PathVariable String acid)
	{
		return trepo.findByAcid(acid);
		
		
	}
	

	

}
